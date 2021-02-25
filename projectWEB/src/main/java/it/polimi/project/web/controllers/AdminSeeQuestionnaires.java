package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.services.QuestionnaireService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AdminSeeQuestionnaires")
public class AdminSeeQuestionnaires extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/QuestionnaireService")
    private QuestionnaireService questionnaireService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkAdminInSession(req, resp)) {
            Map<String, Object> modAttr = new HashMap<>();
            modAttr.put("questionnaires", questionnaireService.findAllQuestionnaires());
            super.redirect(req, resp, "/WEB-INF/AdminSeeQuestionnaires.html", modAttr, null);
        } else {
            resp.sendRedirect("/db2-project/index.html");
        }
    }
}
