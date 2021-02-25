package it.polimi.project.ejb.entities;

import it.polimi.project.ejb.enums.QuestionType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NamedQuery(name="Questionnaire.findByProdId", query = "SELECT q FROM Questionnaire q WHERE q.relatedProduct = ?1 ")
@NamedQuery(name="Questionnaire.findAllQuestionnaires", query = "SELECT q FROM Questionnaire q")
public class Questionnaire implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private Product relatedProduct;

    @OneToMany(mappedBy = "questionnaire", cascade = { CascadeType.PERSIST }, orphanRemoval = true)
    private List<Question> questions;

    @OneToMany(mappedBy = "relatedQuestionnaire")
    private List<UserAnswer> answers;

    private int numMarketingQuestions;
    private int numFixedQuestions;

    public Questionnaire() {
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        numMarketingQuestions = 0;
        numFixedQuestions = 0;
        this.createFixedQuestions();
    }

    private void createFixedQuestions() {
        List<Question> fixedQuestions = new ArrayList<>();
        Question q1 = new Question();
        q1.setDescription("My age is..");
        q1.setType(QuestionType.FIXED);
        fixedQuestions.add(q1);
        Question q2 = new Question();
        q2.setDescription("I identify my gender as..");
        q2.setType(QuestionType.FIXED);
        fixedQuestions.add(q2);
        Question q3 = new Question();
        q3.setDescription("My expertise level is.. [Low, Medium, High]");
        q3.setType(QuestionType.FIXED);
        fixedQuestions.add(q3);

        this.setFixedQuestions(fixedQuestions);
    }

    public void setFixedQuestions(List<Question> fixedQuestions) {
        fixedQuestions.forEach(el -> {
            if(el.getType().equals(QuestionType.FIXED)) {
                el.setQuestionnaire(this);
                this.questions.add(el);
                this.numFixedQuestions++;
            }
        });
    }

    public void addMarketingQuestion(Question q) {
        if(q.getType().equals(QuestionType.MARKETING)) {
            this.questions.add(q);
            q.setQuestionnaire(this);
            this.numMarketingQuestions++;
        }
    }

    public void addAnswer(UserAnswer answer) {
        this.answers.add(answer);
    }
}
