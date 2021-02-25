package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.entities.UserAnswer;
import it.polimi.project.ejb.services.UserAnswerService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CancelAnswer")
public class CancelAnswer extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/UserAnswerService")
    private UserAnswerService userAnswerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkUserInSession(req, resp)) {
            UserAnswer userAnswer = (UserAnswer) super.getSession(req, resp).getAttribute("userAnswer");
            userAnswerService.saveCanceledUserAnswer(userAnswer);
        }
    }
}
