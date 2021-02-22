package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.exceptions.CredentialsException;
import it.polimi.project.ejb.services.ProductService;
import org.apache.commons.lang.StringEscapeUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.ejb.EJB;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Questionnaire")
public class GoToQuestionnairePage extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
    //@EJB(name = "it.polimi.project.ejb.services/ProductService")
    //ProductService productService;

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



            // Redirect to the Home page and add missions to the parameters
            String path = "/WEB-INF/QuestionnairePt1.html";
            ServletContext servletContext = getServletContext();
            final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());

            Product productOfDay = (Product) ctx.getVariable("productOfDay");
            Questionnaire questionnaire;

            if(productOfDay != null) {
                questionnaire = productOfDay.getQuestionnaires().get(0);
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
