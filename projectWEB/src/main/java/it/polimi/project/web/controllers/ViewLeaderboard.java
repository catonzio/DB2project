package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
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

@WebServlet("/Leaderboard")
public class ViewLeaderboard extends MyServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkUserInSession(req, resp)) {

            Product productOfDay = (Product) super.getSession(req, resp).getAttribute("productOfDay");
            Questionnaire questionnaire;

            if (productOfDay != null) {
                questionnaire = productOfDay.getQuestionnaire();
                if (questionnaire != null) {
                    String path = "/WEB-INF/Leaderboard.html";
                    List<UserAnswer> userAnswers= questionnaire.getAnswers();

                    Map<String, Object> modelAttributes = new HashMap<>();
                    modelAttributes.put("userAnswers", userAnswers);

                    super.redirect(req, resp, path, modelAttributes, null);

                }
            }
        }
    }
}
