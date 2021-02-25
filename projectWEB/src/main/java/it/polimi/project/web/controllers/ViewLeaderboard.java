package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Question;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.entities.UserAnswer;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Leaderboard")
public class ViewLeaderboard extends MyServlet {
    @EJB(name = "it.polimi.project.ejb.services/UserAnswerService")
    private UserAnswerService userAnswerService;
    @EJB(name = "it.polimi.project.ejb.services/QuestionnaireService")
    private QuestionnaireService questionnaireService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkUserInSession(req, resp)) {

            Product productOfDay = (Product) super.getSession(req, resp).getAttribute("productOfDay");
            Questionnaire questionnaire;

            if (productOfDay != null) {
                questionnaire = productOfDay.getQuestionnaire();
                if (questionnaire != null) {
                    String path = "/WEB-INF/Leaderboard.html";


                   /* try{
                        questionnaireService.refreshQuestionnaire(questionnaire);
                    }catch (Exception e){
                        questionnaireService.mergeQuestionnaire(questionnaire);
                        questionnaireService.refreshQuestionnaire(questionnaire);
                    }
                    */
                    List<UserAnswer> userAnswers = questionnaire.getAnswers();
                    //List<UserAnswer> userAnswers= userAnswerService.findUserAnswersByQuestionnaire(questionnaire);
                            //questionnaire.getAnswers();


                    Map<String, Object> modelAttributes = new HashMap<>();
                    modelAttributes.put("userAnswers", userAnswers);

                    super.redirect(req, resp, path, modelAttributes, null);

                }
            }
        }
    }
}
