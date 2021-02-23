package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Review;
import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.exceptions.CredentialsException;
import it.polimi.project.ejb.services.ProductService;
import it.polimi.project.ejb.services.ReviewService;
import it.polimi.project.ejb.services.UserService;
import org.apache.commons.lang.StringEscapeUtils;
import org.thymeleaf.context.WebContext;

import javax.ejb.EJB;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.WebConnection;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/NewReview")
public class CreateReview extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/ReviewService")
    private ReviewService reviewService;
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
        String path = "/WEB-INF/WriteReview.html";

        super.redirect(req, resp, path, modelAttributes, null);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        // If the user is not logged in (not present in session) redirect to the login
        String loginpath = getServletContext().getContextPath() + "/index.html";
        HttpSession session = super.getSession(req, resp);
        if (session.isNew() || session.getAttribute("user") == null) {
            resp.sendRedirect(loginpath);
            return;
        }


        // Get and parse all parameters from request
        boolean isBadRequest = false;
        String description = null;

        description =  StringEscapeUtils.escapeJava(req.getParameter("review"));
        isBadRequest = description.isEmpty();
        if (isBadRequest) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect or missing param values");
            return;
        }

       // Create review in DB
        User user = (User) session.getAttribute("user");
        //Product prd = (Product) super.getContext(req, resp).getVariable("productOfDay");
        Product prd = productService.getProductOfDay();
        try {
            reviewService.createReview(prd.getId(), user.getId(), description);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Not possible to create review");
            return;
        }

        Map<String, Object> modelAttributes = new HashMap<>();
        modelAttributes.put("productOfDay", prd);
        String path = "/WEB-INF/Home.html";

        super.redirect(req, resp, path, modelAttributes, null);

    }
}