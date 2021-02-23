package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.exceptions.CredentialsException;
import it.polimi.project.ejb.services.ProductService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/QuestionnairePt1")
public class GoToQuestionnairePt1 extends MyServlet {

    //@EJB(name = "it.polimi.project.ejb.services/ProductService")
    //ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // If the user is not logged in (not present in session) redirect to the login
        String loginpath = getServletContext().getContextPath() + "/index.html";
        HttpSession session = super.getSession(req, resp);
        if (session.isNew() || session.getAttribute("user") == null) {
            resp.sendRedirect(loginpath);
            return;
        }

        Product productOfDay = (Product) super.getContext(req, resp).getVariable("productOfDay");
        Questionnaire questionnaire;

        if(productOfDay != null) {
            questionnaire = productOfDay.getQuestionnaire();
        }
        else{
            questionnaire = new Questionnaire();
        }
        String path = "/WEB-INF/QuestionnairePt1.html";

        Map<String, Object> modelAttributes = new HashMap<>();
        modelAttributes.put("questionnaire", questionnaire);

        super.redirect(req, resp, path, modelAttributes, null);
    }

}
