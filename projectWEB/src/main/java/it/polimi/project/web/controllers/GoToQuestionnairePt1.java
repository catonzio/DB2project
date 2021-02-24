package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Question;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.services.ProductService;
import it.polimi.project.ejb.services.QuestionService;
import it.polimi.project.ejb.services.QuestionnaireService;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/QuestionnairePt1")
public class GoToQuestionnairePt1 extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/QuestionnaireService")
    QuestionnaireService questionnaireService;
    @EJB(name = "it.polimi.project.ejb.services/QuestionService")
    QuestionService questionService;
    @EJB(name = "it.polimi.project.ejb.services/ProductService")
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // If the user is not logged in (not present in session) redirect to the login
        String loginpath = getServletContext().getContextPath() + "/index.html";
        HttpSession session = super.getSession(req, resp);
        if (session.isNew() || session.getAttribute("user") == null) {
            resp.sendRedirect(loginpath);
            return;
        }

        //Product productOfDay = (Product) super.getContext(req, resp).getVariable("productOfDay");
        Product productOfDay = productService.getProductOfDay();
        Questionnaire questionnaire;
        List<Question> marketingQuestions = null;
        List<Question> fixedQuestions = null;

        //if(productOfDay != null) {
            questionnaire = questionnaireService.findQuestionnaireByProdId(productOfDay);
            marketingQuestions = questionService.findMarketingQuestionByQuestionnaireId(questionnaire);
            fixedQuestions = questionService.findFixedQuestionByQuestionnaireId(questionnaire);
        //}
        String path = "/WEB-INF/QuestionnairePt1.html";

        Map<String, Object> modelAttributes = new HashMap<>();
        modelAttributes.put("marketingQuestions", marketingQuestions);
        modelAttributes.put("fixedQuestions", fixedQuestions);

        super.redirect(req, resp, path, modelAttributes, null);
    }

}
