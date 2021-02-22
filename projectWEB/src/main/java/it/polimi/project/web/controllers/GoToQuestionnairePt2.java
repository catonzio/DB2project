package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Questionnaire;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/QuestionnairePt2")
public class GoToQuestionnairePt2 extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;

    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // If the user is not logged in (not present in session) redirect to the login
            String loginpath = getServletContext().getContextPath() + "/index.html";
            HttpSession session = request.getSession();
            if (session.isNew() || session.getAttribute("user") == null) {
                response.sendRedirect(loginpath);
                return;
            }



            String path = "/WEB-INF/QuestionnairePt2.html";
            ServletContext servletContext = getServletContext();
            final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

        Product productOfDay = (Product) ctx.getVariable("productOfDay");
        Questionnaire questionnaire;

        if(productOfDay != null) {
            questionnaire = productOfDay.getQuestionnaire();
        }
        else{
            questionnaire = new Questionnaire();
        }

        ctx.setVariable("questionnaire", questionnaire);
        templateEngine.process(path, ctx, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
    }

}
