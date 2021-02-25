package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.services.QuestionnaireService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/DeleteQuestionnaire")
public class DeleteQuestionnaire extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/QuestionnaireService")
    private QuestionnaireService questionnaireService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkAdminInSession(req, resp)) {
            int id = Integer.parseInt(req.getParameter("questId"));
            Questionnaire questionnaire = questionnaireService.findQuestionnaireById(id);
            LocalDate today = LocalDate.now();

            Map<String, Object> modAttr = new HashMap<>();

            if(questionnaire.getRelatedProduct().getProductOfTheDay().isBefore(today)) {
                if(super.getSession(req, resp).getAttribute("userAnswer") != null)
                    super.getSession(req, resp).removeAttribute("userAnswer");
                /*questionnaire.getAnswers().forEach(ans -> {
                    ans.getRelatedUser().getAnswers().remove(ans);
                });*/
                questionnaire.getAnswers().forEach(ans -> {
                    ans.setRelatedUser(null);
                });
                if (questionnaireService.remove(questionnaire)) {

                    List<Questionnaire> questionnaireList = questionnaireService.findAllQuestionnaires();
                    questionnaireList.sort(Comparator.comparing(o -> o.getRelatedProduct().getProductOfTheDay()));

                    modAttr.put("questionnaires", questionnaireList);
                    super.redirect(req, resp, "/WEB-INF/AdminSeeQuestionnaires.html", modAttr, null);
                }
            } else {
                List<Questionnaire> questionnaireList = questionnaireService.findAllQuestionnaires();
                questionnaireList.sort(Comparator.comparing(o -> o.getRelatedProduct().getProductOfTheDay()));

                modAttr.put("questionnaires", questionnaireList);
                modAttr.put("errorMsg", "You cannot delete a future questionnaire.");
                super.redirect(req, resp, "/WEB-INF/AdminSeeQuestionnaires.html", modAttr, null);
            }
        }
    }
}
