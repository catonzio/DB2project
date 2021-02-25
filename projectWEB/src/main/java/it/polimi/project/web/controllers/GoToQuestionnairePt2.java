package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.*;
import it.polimi.project.ejb.enums.QuestionType;
import it.polimi.project.ejb.services.QuestionService;
import it.polimi.project.ejb.services.QuestionnaireService;
import it.polimi.project.ejb.services.UserAnswerService;
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
import java.util.stream.Collectors;

@WebServlet("/QuestionnairePt2")
public class GoToQuestionnairePt2 extends MyServlet {

    /*@EJB(name = "it.polimi.project.ejb.services/QuestionnaireService")
    private QuestionnaireService questionnaireService;
    @EJB(name = "it.polimi.project.ejb.services/QuestionService")
    QuestionService questionService;*/
    @EJB(name = "it.polimi.project.ejb.services/UserAnswerService")
    private UserAnswerService userAnswerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean userInSession = super.checkUserInSession(req, resp);
        if(userInSession) {
            HttpSession session = super.getSession(req, resp);
            Questionnaire questionnaire = (Questionnaire) session.getAttribute("questionnaire");
            User user = (User) session.getAttribute("user");
            Map<String, Object> modelAttributes = new HashMap<>();

            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setRelatedUser(user);

            List<Question> marketingQuestions = questionnaire.getQuestions().stream()
                                                            .filter(el -> el.getType()
                                                            .equals(QuestionType.MARKETING))
                                                            .collect(Collectors.toList());

            //super.getContext(req, resp).setVariable("badwords", false);
            session.setAttribute("badwords", false);
            for(int i=0; i<marketingQuestions.size(); i++) {
                String answ = req.getParameter("answer"+(i+1));

                Boolean result = userAnswerService.checkForBadWords(answ);
                if(result)
                    session.setAttribute("badwords", true);

                userAnswer.addAnswer(marketingQuestions.get(i), answ);

            }

            Map<String, Object> sessionAttributes = new HashMap<>();
            sessionAttributes.put("userAnswer", userAnswer);


            List<String> fixedQuests = questionnaire.getQuestions().stream()
                    .filter(question -> question.getType().equals(QuestionType.FIXED))
                    .map(Question::getDescription)
                    .collect(Collectors.toList());

            modelAttributes.put("fixedQuestions", fixedQuests);

            super.redirect(req, resp, "/WEB-INF/QuestionnairePt2.html", modelAttributes, sessionAttributes);
        }
    }
}
