package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Admin;
import it.polimi.project.ejb.exceptions.CredentialsException;
import it.polimi.project.ejb.services.AdminService;
import org.apache.commons.lang.StringEscapeUtils;

import javax.ejb.EJB;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AdminLogin")
public class AdminLogin extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/AdminService")
    AdminService adminService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = null;
        String pwd = null;
        try {
            email = req.getParameter("email");
            pwd = req.getParameter("password");
            if (email == null && pwd == null) {
                super.redirect(req, resp, "/WEB-INF/AdminLogin.html", null, null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // obtain and escape params
        String email = null;
        String pwd = null;

        try {
            email = StringEscapeUtils.escapeJava(request.getParameter("email"));
            pwd = StringEscapeUtils.escapeJava(request.getParameter("password"));
            if (email == null || pwd == null || email.isEmpty() || pwd.isEmpty()) {
                throw new Exception("Missing or empty credential value");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
            return;
        }

        Admin admin;
        try {
            // query db to authenticate for user
            admin = adminService.checkCredentials(email, pwd);
        } catch (CredentialsException | NonUniqueResultException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
            return;
        }

        String path;
        Map<String, Object> modelAttributes = new HashMap<>();
        Map<String, Object> sessionAttributes = new HashMap<>();
        if (admin == null) {
            modelAttributes.put("errorMsg", "Incorrect username or password");
            path = "/WEB-INF/AdminLogin.html";
        } else {
            sessionAttributes.put("admin", admin);
            //modelAttributes.put("admin", admin);
            path = "/WEB-INF/AdminHome.html";
        }
        super.redirect(request, response, path, modelAttributes, sessionAttributes);
    }

}
