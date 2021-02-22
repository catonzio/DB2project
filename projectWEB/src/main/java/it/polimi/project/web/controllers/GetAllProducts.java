package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.services.ProductService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/GetAllProducts")
public class GetAllProducts extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/ProductService")
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Product> products = productService.findAllProducts();
            String path = "/WEB-INF/AdminHome.html";
            Map<String, Object> modelAttributes = new HashMap<>();
            modelAttributes.put("admin", super.getSession(req, resp).getAttribute("admin"));
            modelAttributes.put("products", products);

            super.redirect(req, resp, path, modelAttributes, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
