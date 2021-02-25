package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Question;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.entities.UserAnswer;
import it.polimi.project.ejb.enums.QuestionType;
import it.polimi.project.ejb.services.UserAnswerService;

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
import java.util.stream.Collectors;

@WebServlet("/QuestionnaireCompleted")
public class QuestionnaireCompleted extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/UserAnswerService")
    private UserAnswerService userAnswerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkUserInSession(req, resp)) {
            HttpSession session = super.getSession(req, resp);
            Questionnaire questionnaire = (Questionnaire) session.getAttribute("questionnaire");
            UserAnswer userAnswer = (UserAnswer) session.getAttribute("userAnswer");

            List<Question> fixedQuestions = questionnaire.getQuestions().stream()
                                                        .filter(el -> el.getType()
                                                        .equals(QuestionType.FIXED))
                                                        .collect(Collectors.toList());
            for(int i=0; i<fixedQuestions.size(); i++) {
                String answ = req.getParameter("answer" + (i + 1));
                userAnswer.addAnswer(fixedQuestions.get(i), answ);
            }

            String path = "/WEB-INF/QuestionnaireCompleted.html";
            if(userAnswerService.saveSubmittedUserAnswer(userAnswer)) {
                Map<String, Object> sessionAttributes = new HashMap<>();
                sessionAttributes.put("userAnswer", userAnswer);
                super.redirect(req, resp, path, null, sessionAttributes);
            } else {
                Map<String, Object> modelAttributes = new HashMap<>();
                modelAttributes.put("errorMsg", "Failed to save answers.");
                super.redirect(req, resp, path, modelAttributes, null);
            }

        }
    }
}
