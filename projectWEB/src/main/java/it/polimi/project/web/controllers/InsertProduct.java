package it.polimi.project.web.controllers;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Question;
import it.polimi.project.ejb.entities.Questionnaire;
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

            q.setProduct(p);
            p.setQuestionnaire(q);

            if(productService.saveNewProduct(p) && questionnaireService.saveNewQuestionnaire(q)) {
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
        List<String> variableAnswers = new ArrayList<>();
        int i = 1;
        //Get all the questions and the answers; since their number is not fixed, we try until we find.
        while(true) {
            String questParam = req.getParameter("question"+i);
            String answerParam = req.getParameter("answer"+i);
            if(questParam != null && answerParam != null) {
                variableQuestions.add(questParam);
                variableAnswers.add(answerParam);
                i++;
            } else
                break;
        }
        if(variableQuestions.size() != variableAnswers.size())
            throw new Exception("Different number of questions and answers!");
        for(i = 0; i<variableQuestions.size(); i++) {
            Question question = new Question();
            question.setDescription(variableQuestions.get(i));
            question.setAnswer(variableAnswers.get(i));
            question.setType("marketing");
            q.addMarketingQuestion(question);
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
