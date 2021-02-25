package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.services.ProductService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AdminSeeProducts")
public class AdminSeeProducts extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/ProductService")
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkAdminInSession(req, resp)) {
            Map<String, Object> modelAttributes = new HashMap<>();

            List<Product> productList = productService.findAllProducts();
            productList.sort(Comparator.comparing(Product::getProductOfTheDay));

            modelAttributes.put("products", productList);
            redirect(req, resp, "/WEB-INF/AdminSeeProducts.html", modelAttributes, null);
        } else {
            resp.sendRedirect("/db2-project/index.html");
        }
    }
}
