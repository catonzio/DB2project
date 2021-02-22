package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.exceptions.CredentialsException;
import it.polimi.project.ejb.services.ProductService;
import it.polimi.project.ejb.services.UserService;
import org.apache.commons.lang.StringEscapeUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.ejb.EJB;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/CheckLogin")
public class CheckLogin extends MyServlet {

	@EJB(name = "it.polimi.project.ejb.services/UserService")
	private UserService usrService;
	@EJB(name = "it.polimi.project.ejb.services/ProductService")
	private ProductService productService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String usrn;
		String pwd;
		try {
			usrn = StringEscapeUtils.escapeJava(request.getParameter("username"));
			pwd = StringEscapeUtils.escapeJava(request.getParameter("pwd"));
			if (usrn == null || pwd == null || usrn.isEmpty() || pwd.isEmpty()) {
				throw new Exception("Missing or empty credential value");
			}

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
			return;
		}

		User user;
		try {
			user = usrService.checkCredentials(usrn, pwd);
		} catch (CredentialsException | NonUniqueResultException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
			return;
		}

		// If the user exists, add info to the session and go to home page, otherwise
		// show login page with error message

		String path;
		Map<String, Object> modelAttributes = new HashMap<>();
		Map<String, Object> sessionAttributes = new HashMap<>();

		if (user == null) {
			modelAttributes.put("errorMsg", "Incorrect username or password");
			path = "/index.html";
		} else {
			sessionAttributes.put("user", user);
			modelAttributes.put("productOfDay", productService.getProductOfDay());
			path = "/WEB-INF/Home.html";
		}
		super.redirect(request, response, path, modelAttributes, sessionAttributes);
	}

}