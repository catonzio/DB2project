package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Questionnaire;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/QuestionnairePt2")
public class GoToQuestionnairePt2 extends MyServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // If the user is not logged in (not present in session) redirect to the login
        String loginpath = getServletContext().getContextPath() + "/index.html";
        HttpSession session = super.getSession(request, response);
        if (session.isNew() || session.getAttribute("user") == null) {
            response.sendRedirect(loginpath);
            return;
        }

        Product productOfDay = (Product) super.getContext(request, response).getVariable("productOfDay");
        Questionnaire questionnaire;

        if(productOfDay != null) {
            questionnaire = productOfDay.getQuestionnaire();
        }
        else{
            questionnaire = new Questionnaire();
        }

        String path = "/WEB-INF/QuestionnairePt2.html";
        Map<String, Object> modelAttributes = new HashMap<>();
        modelAttributes.put("questionnaire", questionnaire);

        super.redirect(request, response, path, modelAttributes, null);
    }

}
