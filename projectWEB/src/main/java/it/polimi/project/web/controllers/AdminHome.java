package it.polimi.project.web.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminHome")
public class AdminHome extends MyServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(super.checkAdminInSession(req, resp)) {
            super.redirect(req, resp, "/WEB-INF/AdminHome.html", null, null);
        } else {
            resp.sendRedirect("/db2-project/index.html");
        }
    }
}
