package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Question;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.enums.QuestionType;
import it.polimi.project.ejb.services.ProductService;
import it.polimi.project.ejb.services.QuestionnaireService;
import org.apache.commons.io.IOUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet("/InsertProduct")
@MultipartConfig
public class InsertProduct extends MyServlet {

    @EJB(name = "it.polimi.project.ejb.services/ProductService")
    private ProductService productService;

    @EJB(name = "it.polimi.project.ejb.services/QuestionnaireService")
    private QuestionnaireService questionnaireService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        Part filePart = req.getPart("image");
        String fileName = getFileName(filePart);
        InputStream fileContent = null;

        if(fileName == null || (!fileName.endsWith(".jpg") && !fileName.endsWith(".png")))
            redirect(req, resp, "Image format not correct.");

        try {
            fileContent = filePart.getInputStream();
            byte[] bytes = IOUtils.toByteArray(fileContent);

            Questionnaire q = extractQuestionnaire(req);
            Product p = extractProduct(req);
            p.setPhotoimage(bytes);

            q.setRelatedProduct(p);
            p.setQuestionnaire(q);

            // && questionnaireService.saveQuestionnaire(q)
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

    private Questionnaire extractQuestionnaire(HttpServletRequest req) throws Exception {
        Questionnaire q = new Questionnaire();
        List<String> variableQuestions = new ArrayList<>();
        try {
            String numStr = req.getParameter("questions_counter");
            int numberOfQuestions = Integer.parseInt(numStr);
            for(int i=0; i<numberOfQuestions; i++) {
                String questParam = req.getParameter("question"+(i+1));
                if(questParam != null) {
                    variableQuestions.add(questParam);
                }
            }
            if(variableQuestions.size() != numberOfQuestions)
                throw new Exception("Different number of questions and counter!");
            for(String variableQuestion : variableQuestions) {
                Question question = new Question();
                question.setDescription(variableQuestion);
                question.setType(QuestionType.MARKETING);
                q.addMarketingQuestion(question);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return q;
    }

    private Product extractProduct(HttpServletRequest req) {
        String name = req.getParameter("name");
        LocalDate date = LocalDate.parse(req.getParameter("date"));

        Product p = new Product();
        p.setName(name);
        p.setProductOfTheDay(date);
        return p;
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp, String message) {
        String path = "/WEB-INF/AdminHome.html";
        Map<String, Object> modelAttributes = new HashMap<>();
        modelAttributes.put("admin", super.getSession(req, resp).getAttribute("admin"));
        modelAttributes.put("message", message);

        super.redirect(req, resp, path, modelAttributes, null);
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
