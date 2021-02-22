package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.services.ProductService;
import org.apache.commons.io.IOUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.FileSystems;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@WebServlet("/InsertProduct")
@MultipartConfig
public class InsertProduct extends HttpServlet {

    @EJB(name = "it.polimi.project.ejb.services/ProductService")
    private ProductService productService;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getServletContext().getRealPath("/");
        path = path.substring(0, path.indexOf("db2-project") + 12) + "images";

        Part filePart = req.getPart("image");
        String fileName = getFileName(filePart);

        InputStream fileContent = null;

        if(fileName == null || (!fileName.endsWith(".jpg") && !fileName.endsWith(".png")))
            redirect(req, resp, "Image format not correct.");

        try {
            fileContent = filePart.getInputStream();
            byte[] bytes = IOUtils.toByteArray(fileContent);

            String name = req.getParameter("name");
            Date date = extractDate(req.getParameter("date"));

            Product p = new Product();
            p.setName(name);
            p.setProductOfTheDay(date);
            p.setPhotoimage(bytes);

            if(productService.saveNewProduct(p)) {
                redirect(req, resp, "Correctly saved!");
            } else {
                redirect(req, resp, "Unable to save the product.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(fileContent != null) {
                fileContent.close();
            }
        }
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, String message) {
        try {
            String path = "/WEB-INF/AdminHome.html";
            ServletContext servletContext = getServletContext();
            final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
            ctx.setVariable("admin", ctx.getSession().getAttribute("admin"));
            ctx.setVariable("message", message);
            templateEngine.process(path, ctx, resp.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Date extractDate(String dateS) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "uuuu-MM-dd" );
        LocalDate ld = LocalDate.parse(dateS , f );
        Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }

    private String getFileName(Part filePart) {
        for(String content : filePart.getHeader("content-disposition").split(";")) {
            if(content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 1)
                        .trim()
                        .replace("\"", "");
            }
        }
        return null;
    }
}
