package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Question;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.services.QuestionnaireService;
import org.apache.commons.lang.StringEscapeUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/QuestionnairePt2")
public class GoToQuestionnairePt2 extends MyServlet {
    @EJB(name = "it.polimi.project.ejb.services/QuestionnaireService")
    private QuestionnaireService questionnaireService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // If the user is not logged in (not present in session) redirect to the login
        String loginpath = getServletContext().getContextPath() + "/index.html";
        HttpSession session = super.getSession(request, response);
        if (session.isNew() || session.getAttribute("user") == null) {
            response.sendRedirect(loginpath);
            return;
        }

        //Product productOfDay = (Product) super.getContext(request, response).getVariable("productOfDay");
        Questionnaire questionnaire;
        questionnaire = (Questionnaire) super.getContext(request, response).getVariable("questionnaire");

        if(questionnaire == null) {
            questionnaire = new Questionnaire();
            //ERROR
        }


        List<Question> mqsts = questionnaire.getMarketingQuestions();
        if(mqsts != null && !mqsts.isEmpty()) {
            mqsts.forEach(question -> {
                String answer = StringEscapeUtils.escapeJava(request.getParameter("question.id"));
                question.setAnswer(answer);
            });
        }

        String path = "/WEB-INF/QuestionnairePt2.html";
        Map<String, Object> modelAttributes = new HashMap<>();
        modelAttributes.put("questionnaire", questionnaire);

        super.redirect(request, response, path, modelAttributes, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        Questionnaire questionnaire;
        questionnaire = (Questionnaire) super.getContext(request, response).getVariable("questionnaire");

        List<Question> fqsts = questionnaire.getFixedQuestions();
        fqsts.forEach(question -> {
            String answer = StringEscapeUtils.escapeJava(request.getParameter("question.id"));
            question.setAnswer(answer);
        });

        boolean result = questionnaireService.saveQuestionnaire(questionnaire);

        String path = "/WEB-INF/AdminHome.html";
        Map<String, Object> modelAttributes = new HashMap<>();
        if (!result) {
            modelAttributes.put("errorMsg", "Incorrect inputs");
            path = "/WEB-INF/QuestionnairePt2.html";
        } else {
            modelAttributes.put("productOfDay", super.getContext(request,response).getVariable("productOfDay"));
            path = "/WEB-INF/Home.html";
        }
        super.redirect(request, response, path, modelAttributes, null);
    }

}
