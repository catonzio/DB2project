package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.services.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerUser")
public class RegistrationServlet extends HttpServlet {

    private TemplateEngine templateEngine;

    @EJB(name = "it.polimi.project.ejb.services/UserService")
    private UserService userService;

    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username;
        String password;
        String email;
        try {
            username = req.getParameter("username");
            password = req.getParameter("password");
            email = req.getParameter("email");

            if(username == null || username.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty())
                throw new Exception("Missing username or password or email");

        } catch (Exception ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
            return;
        }

        User user = new User();

        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        boolean result = userService.saveUser(user);

        String path;

        if (!result) {
            ServletContext servletContext = getServletContext();
            final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
            ctx.setVariable("errorMsg", "Incorrect username or password");
            path = "/index.html";
            templateEngine.process(path, ctx, resp.getWriter());
        } else {
            //request.getSession().setAttribute("user", user);
            path = getServletContext().getContextPath() + "/yesPage";
            resp.sendRedirect(path);
        }

        //final WebContext ctx = new WebContext(req, resp, getServletContext(), req.getLocale());
        //ctx.setVariable("user", user);


    }
}
