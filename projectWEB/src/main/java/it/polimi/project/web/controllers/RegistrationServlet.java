package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.exceptions.CredentialsException;
import it.polimi.project.ejb.services.UserService;
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
import java.io.IOException;

@WebServlet("/RegisterUser")
public class RegistrationServlet extends HttpServlet {

    private TemplateEngine templateEngine;

    @EJB(name = "it.polimi.project.ejb.services/UserService")
    private UserService usrService;

    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usrn = null;
        String pwd = null;
        String email = null;
        String name = null;
        String surname = null;
        User user = new User();
        try {
            usrn = StringEscapeUtils.escapeJava(request.getParameter("username"));
            pwd = StringEscapeUtils.escapeJava(request.getParameter("pwd"));
            name = StringEscapeUtils.escapeJava(request.getParameter("name"));
            surname = StringEscapeUtils.escapeJava(request.getParameter("surname"));
            email = StringEscapeUtils.escapeJava(request.getParameter("email"));
            if (usrn == null || pwd == null || email == null || name == null || surname == null || usrn.isEmpty() || pwd.isEmpty()
                    || email.isEmpty() || name.isEmpty() || surname.isEmpty() ) {
                throw new Exception("Missing or empty credential value");
            }
            user.setEmail(email);
            user.setUsername(usrn);
            user.setPassword(pwd);
            user.setName(name);
            user.setSurname(surname);

        } catch (Exception e) {
            // for debugging only e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
            return;
        }

        boolean result = usrService.saveUser(user);

/*        try {
            // query db to authenticate for user
            user = usrService.checkCredentials(usrn, pwd);
        } catch (CredentialsException | NonUniqueResultException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
            return;
        }
*/

        String path;
        if (user == null) {
            ServletContext servletContext = getServletContext();
            final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
            ctx.setVariable("errorMsg", "Incorrect username or password");
            path = "/index.html";
            templateEngine.process(path, ctx, response.getWriter());
        } else {
            request.getSession().setAttribute("user", user);
            path = getServletContext().getContextPath() + "/Home";
            response.sendRedirect(path);
        }


         /*


        final WebContext ctx = new WebContext(req, resp, getServletContext(), req.getLocale());
        ctx.setVariable("user", user);

        req.getSession().setAttribute("user", user);
        path = getServletContext().getContextPath() + "/Home";
        resp.sendRedirect(path);
*/

    }
}
