package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.services.ProductService;
import it.polimi.project.ejb.services.UserService;
import org.apache.commons.lang.StringEscapeUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/RegisterUser")
public class Registration extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/UserService")
    private UserService usrService;

    @EJB(name = "it.polimi.project.ejb.services/ProductService")
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/WEB-INF/Registration.html";
        super.redirect(req, resp, path, null, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String path;
        Map<String, Object> modelAttributes = new HashMap<>();
        Map<String, Object> sessionAttributes = new HashMap<>();

        String usrn = null;
        String pwd = null;
        String email = null;
        String name = null;
        String surname = null;
        try {
            usrn = StringEscapeUtils.escapeJava(request.getParameter("username"));
            pwd = StringEscapeUtils.escapeJava(request.getParameter("pwd"));
            name = StringEscapeUtils.escapeJava(request.getParameter("name"));
            surname = StringEscapeUtils.escapeJava(request.getParameter("surname"));
            email = StringEscapeUtils.escapeJava(request.getParameter("email"));
            if (usrn == null || pwd == null || email == null || name == null || surname == null || usrn.isEmpty() || pwd.isEmpty()
                    || email.isEmpty() || name.isEmpty() || surname.isEmpty() ) {
                modelAttributes.put("errorMsg", "Missing field");
                path = "/WEB-INF/Registration.html";
                super.redirect(request, response, path, modelAttributes, sessionAttributes);
            }
            if(!usrService.findUserByUsrnEmail(usrn, email).isEmpty()) {
                modelAttributes.put("errorMsg", "Username or password already taken");
                path = "/WEB-INF/Registration.html";
                super.redirect(request, response, path, modelAttributes, sessionAttributes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(usrn);
        user.setPassword(pwd);
        user.setName(name);
        user.setSurname(surname);

        boolean result = usrService.saveUser(user);

        if (!result) {
            modelAttributes.put("errorMsg", "Incorrect username or password");
            path = "/WEB-INF/Registration.html";
        } else {
            sessionAttributes.put("user", user);
            sessionAttributes.put("productOfDay", productService.getProductOfDay());
            path = "/WEB-INF/Home.html";
        }
        super.redirect(request, response, path, modelAttributes, sessionAttributes);
    }
}
