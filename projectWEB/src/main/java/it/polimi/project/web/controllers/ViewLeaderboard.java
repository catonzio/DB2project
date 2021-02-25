package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.*;
import it.polimi.project.ejb.enums.QuestionType;
import it.polimi.project.ejb.services.QuestionnaireService;
import it.polimi.project.ejb.services.UserAnswerService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/Leaderboard")
public class ViewLeaderboard extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/UserAnswerService")
    private UserAnswerService userAnswerService;
    @EJB(name = "it.polimi.project.ejb.services/QuestionnaireService")
    private QuestionnaireService questionnaireService;
/*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkUserInSession(req, resp)) {
            Product product = (Product) super.getSession(req, resp).getAttribute("productOfDay");
            if(product != null) {
                Questionnaire questionnaire = product.getQuestionnaire();
                if(questionnaire != null) {
                    /*List<UserAnswer> answers = q.getAnswers();
                    Set<User> usersSet = new HashSet<>();
                    answers.forEach(el -> {
                        usersSet.add(el.getRelatedUser());
                    });
                    List<User> users = usersSet.stream().sorted(Comparator.comparingInt(User::getPoints)).collect(Collectors.toList());

                    Map<String, Object> modelAttributes = new HashMap<>();
                    modelAttributes.put("users", users);

                    //questionnaireService.persistQuestionnaire(questionnaire);
                    List<UserAnswer> userAnswers = questionnaire.getAnswers();
                    // List<UserAnswer> userAnswers= userAnswerService.findUserAnswersByQuestionnaire(questionnaire);

                    Map<String, Object> modelAttributes = new HashMap<>();
                    modelAttributes.put("userAnswers", userAnswers);

                    super.redirect(req, resp, "/WEB-INF/Leaderboard.html", modelAttributes, null);
                }
            }
            //super.redirect(req, resp, "/WEB-INF/Home.html", null, null);
        }
    }
    */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (super.checkUserInSession(req, resp)) {

            Product productOfDay = (Product) super.getSession(req, resp).getAttribute("productOfDay");
            Questionnaire questionnaire;

            if (productOfDay != null) {
                questionnaire = productOfDay.getQuestionnaire();
                if (questionnaire != null) {
                    String path = "/WEB-INF/Leaderboard.html";

                    questionnaireService.mergeQuestionnaire(questionnaire);
                    List<UserAnswer> userAnswers = questionnaire.getAnswers();
                    userAnswers.forEach( a -> {
                        userAnswerService.mergeAnswer(a);
                    });
                    //userAnswerService.mergeAnswers(userAnswers);
                    // List<UserAnswer> userAnswers= userAnswerService.findUserAnswersByQuestionnaire(questionnaire);

                    Map<String, Object> modelAttributes = new HashMap<>();
                    modelAttributes.put("userAnswers", userAnswers);

                    super.redirect(req, resp, path, modelAttributes, null);

                }
            }
        }

    }
}

