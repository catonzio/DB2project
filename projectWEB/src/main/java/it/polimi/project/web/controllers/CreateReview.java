package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Review;
import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.entities.UserAnswer;
import it.polimi.project.ejb.exceptions.CredentialsException;
import it.polimi.project.ejb.services.ProductService;
import it.polimi.project.ejb.services.ReviewService;
import it.polimi.project.ejb.services.UserAnswerService;
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
    @EJB(name = "it.polimi.project.ejb.services/UserAnswerService")
    private UserAnswerService userAnswerService;
    @EJB(name = "it.polimi.project.ejb.services/UserService")
    private UserService userService;
    @EJB(name = "it.polimi.project.ejb.services/ProductService")
    private ProductService productService;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        // If the user is not logged in (not present in session) redirect to the login
        /*String loginpath = getServletContext().getContextPath() + "/index.html";
        HttpSession session = super.getSession(req, resp);
        if (session.isNew() || session.getAttribute("user") == null) {
            resp.sendRedirect(loginpath);
            return;
        }*/

        //Product productOfDay = productService.getProductOfDay();
        //Map<String, Object> modelAttributes = new HashMap<>();
        //modelAttributes.put("productOfDay", productOfDay);
        if (super.checkUserInSession(req, resp)) {
            String path = "/WEB-INF/WriteReview.html";
            super.redirect(req, resp, path, null, null);
        } else {
            resp.sendRedirect("/db2-project/index.html");
        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        // If the user is not logged in (not present in session) redirect to the login
        /*String loginpath = getServletContext().getContextPath() + "/index.html";
        HttpSession session = super.getSession(req, resp);
        if (session.isNew() || session.getAttribute("user") == null) {
            resp.sendRedirect(loginpath);
            return;
        }*/
        HttpSession session = super.getSession(req, resp);
        User user = (User) session.getAttribute("user");

        if(super.checkUserInSession(req, resp)) {
            // Get and parse all parameters from request
            boolean isBadRequest = false;
            String description = null;

            description = req.getParameter("review").trim();
            isBadRequest = description.isEmpty();
            if (isBadRequest) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect or missing param values");
                return;
            }
            Boolean result = userAnswerService.checkForBadWords(description);
            if(result){
                userService.blockUser(user);
                session = req.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                String path = getServletContext().getContextPath() + "/Blocked.html";
                resp.sendRedirect(path);
                return;
            }

            // Create review in DB
            //HttpSession session = super.getSession(req, resp);
            //User user = (User) session.getAttribute("user");
            Product prd = (Product) session.getAttribute("productOfDay");

            Review newReview = new Review(LocalDate.now(), description, user, prd);
            prd.addReview(newReview);
            user.addReview(newReview);

            //Product prd = productService.getProductOfDay();
            try {
                reviewService.saveReview(newReview);
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Not possible to create review");
                return;
            }

            if(productService.refreshProduct(prd)) {
                //Product prd2 = productService.getProductOfDay();
                Map<String, Object> sessionAttributes = new HashMap<>();
                sessionAttributes.put("productOfDay", prd);
                String path = "/WEB-INF/Home.html";

                super.redirect(req, resp, path, null, sessionAttributes);
            }
        }
    }
}
