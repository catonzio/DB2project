package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Question;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.enums.QuestionType;
import it.polimi.project.ejb.services.ProductService;
import it.polimi.project.ejb.services.QuestionnaireService;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@WebServlet("/QuestionnairePt1")
public class GoToQuestionnairePt1 extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/ProductService")
    ProductService productService;
    @EJB(name = "it.polimi.project.ejb.services/QuestionnaireService")
    private QuestionnaireService questionnaireService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(super.checkUserInSession(req, resp)) {

            Product productOfDay = (Product) super.getSession(req, resp).getAttribute("productOfDay");
            Questionnaire questionnaire;

            if(productOfDay != null) {
                //questionnaire = productOfDay.getQuestionnaire();
                questionnaire = questionnaireService.findQuestionnaireByProdId(productOfDay);
                if(questionnaire != null) {
                    String path = "/WEB-INF/QuestionnairePt1.html";

                    Map<String, Object> sessionAttributes = new HashMap<>();
                    Map<String, Object> modelAttributes = new HashMap<>();

                    sessionAttributes.put("questionnaire", questionnaire);

                    if(checkIfAlreadyAnswered((User) getSession(req, resp).getAttribute("user"), questionnaire)) {
                        modelAttributes.put("errorMsg", "You have already completed this questionnaire!");
                    } else {
                        List<Question> markQuests = questionnaire.getQuestions().stream()
                                .filter(question -> question.getType().equals(QuestionType.MARKETING))
                                .collect(Collectors.toList());
                        modelAttributes.put("marketingQuestions", markQuests);
                    }
                    super.redirect(req, resp, path, modelAttributes, sessionAttributes);
                } else {
                    super.redirect(req, resp, "/WEB-INF/Home.html", null, null);
                }
            }
        } else {
            resp.sendRedirect("/db2-project/index.html");
        }
    }

    private boolean checkIfAlreadyAnswered(User user, Questionnaire questionnaire) {
        return user.getAnswerByQuestionnaire(questionnaire) != null;
    }

}
