package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.services.ProductService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/SeeImg")
public class SeeImg extends MyServlet {

    @EJB
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(super.checkAdminInSession(req, resp)) {
            int id = Integer.parseInt(req.getParameter("imgId"));
            Product product = productService.findProductById(id);
            if(product != null) {
                Map<String, Object> modAttr = new HashMap<>();
                modAttr.put("img", product.getBase64Img());
                super.redirect(req, resp, "/WEB-INF/SeeImg.html", modAttr, null);
            }
        }
    }
}
