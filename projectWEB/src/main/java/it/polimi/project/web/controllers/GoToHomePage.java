package it.polimi.project.web.controllers;


import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.entities.Review;
import it.polimi.project.ejb.services.ProductService;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Home")
public class GoToHomePage extends MyServlet {

	@EJB(name = "it.polimi.project.ejb.services/ProductService")
	private ProductService productService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// If the user is not logged in (not present in session) redirect to the login
		String loginpath = getServletContext().getContextPath() + "/index.html";
		HttpSession session = super.getSession(req, resp);
		if (session.isNew() || session.getAttribute("user") == null) {
			resp.sendRedirect(loginpath);
			return;
		}

		Product productOfDay = productService.getProductOfDay();
		Map<String, Object> modelAttributes = new HashMap<>();
		modelAttributes.put("productOfDay", productOfDay);
		String path = "/WEB-INF/Home.html";

		super.redirect(req, resp, path, modelAttributes, null);
	}

}
