package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Question;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.entities.UserAnswer;
import it.polimi.project.ejb.enums.QuestionType;
import it.polimi.project.ejb.services.UserAnswerService;
import it.polimi.project.ejb.services.UserService;

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
    @EJB(name = "it.polimi.project.ejb.services/UserService")
    private UserService userService;

    private Boolean result;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkUserInSession(req, resp)) {
            HttpSession session = super.getSession(req, resp);
            User user = (User) session.getAttribute("user");
            Questionnaire questionnaire = (Questionnaire) session.getAttribute("questionnaire");

            if(user != null && questionnaire != null) {
                UserAnswer userAnswer = user.getAnswerByQuestionnaire(questionnaire);
                session.setAttribute("userAnswer", userAnswer);
                super.redirect(req, resp, "/WEB-INF/QuestionnaireCompleted.html", null, null);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkUserInSession(req, resp)) {
            HttpSession session = super.getSession(req, resp);
            Questionnaire questionnaire = (Questionnaire) session.getAttribute("questionnaire");
            UserAnswer userAnswer = (UserAnswer) session.getAttribute("userAnswer");
            User user = (User) session.getAttribute("user");

            //result = (Boolean) super.getContext(req, resp).getVariable("badwords");
            result = (Boolean) session.getAttribute("badwords");
            if(result != null && result){
                userService.blockUser(user);
                session = req.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                String path = getServletContext().getContextPath() + "/Blocked.html";
                resp.sendRedirect(path);
                return;
            }



            List<Question> fixedQuestions = questionnaire.getQuestions().stream()
                                                        .filter(el -> el.getType()
                                                        .equals(QuestionType.FIXED))
                                                        .collect(Collectors.toList());
            for(int i=0; i<fixedQuestions.size(); i++) {
                if(fixedQuestions.get(i).getType().equals(QuestionType.FIXED)) {
                    String answ = req.getParameter("answer" + (i + 1));
                    if(answ != null && !answ.isEmpty()){

                        result = userAnswerService.checkForBadWords(answ);
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
                        userAnswer.addAnswer(fixedQuestions.get(i), answ);

                    }
                }
            }

            String path = "/WEB-INF/QuestionnaireCompleted.html";
            if(userAnswerService.saveUserAnswer(userAnswer)) {
                userAnswerService.mergeAnswer(userAnswer);
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
