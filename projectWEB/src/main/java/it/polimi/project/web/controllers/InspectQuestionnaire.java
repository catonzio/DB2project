package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.entities.UserAnswer;
import it.polimi.project.ejb.enums.AnswerStatus;
import it.polimi.project.ejb.services.QuestionnaireService;
import it.polimi.project.ejb.services.UserAnswerService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/InspectQuestionnaire")
public class InspectQuestionnaire extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/QuestionnaireService")
    private QuestionnaireService questionnaireService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkAdminInSession(req, resp)) {
            int questId = Integer.parseInt(req.getParameter("questId"));
            Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questId);
            if(questionnaire != null) {
                List<UserAnswer> answers = questionnaire.getAnswers();
                if(answers != null) {
                    Map<String, Object> modAttr = new HashMap<>();
                    modAttr.put("userAnswers", answers);
                    super.redirect(req, resp, "/WEB-INF/InspectQuestionnaire.html", modAttr, null);
                }

//                List<User> userSubmitted = new ArrayList<>();
//                List<User> userCancelled = new ArrayList<>();
//                answers.forEach(el -> {
//                    if(el.getStatus().equals(AnswerStatus.SUBMITTED)) {
//                        userSubmitted.add(el.getRelatedUser());
//
//
//                    } else if(el.getStatus().equals(AnswerStatus.CANCELLED)) {
//                        userCancelled.add(el.getRelatedUser());
//                    }
//                });

            }
        }
    }
}
