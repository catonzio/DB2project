package it.polimi.project.web.controllers;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;

    public void init() {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    public void redirect(HttpServletRequest req,
                         HttpServletResponse resp,
                         String path,
                         Map<String, Object> modelAttributes,
                         Map<String, Object> sessionAttributes) {

        try {
            ServletContext servletContext = getServletContext();
            final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());

            if(sessionAttributes != null && !sessionAttributes.isEmpty()) {
                sessionAttributes.forEach((k, v) -> {
                    req.getSession().setAttribute(k, v);
                });
            }
            if(modelAttributes != null && !modelAttributes.isEmpty()) {
                modelAttributes.forEach(ctx::setVariable);
            }
            templateEngine.process(path, ctx, resp.getWriter());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkUserInSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String loginpath = getServletContext().getContextPath() + "/index.html";
        HttpSession session = getSession(req, resp);
        if (session == null || session.isNew() || session.getAttribute("user") == null) {
            return false;
        }
        return true;
    }

    public HttpSession getSession(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = getServletContext();
        return new WebContext(req, resp, servletContext, req.getLocale()).getSession();
    }

    public WebContext getContext(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = getServletContext();
        return new WebContext(req, resp, servletContext, req.getLocale());
    }
}
