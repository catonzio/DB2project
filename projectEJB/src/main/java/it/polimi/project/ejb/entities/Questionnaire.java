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
public class Questionnaire implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    private Product relatedProduct;

    @OneToMany(mappedBy = "questionnaire", cascade = { CascadeType.PERSIST })
    private List<Question> marketingQuestions;

    @OneToMany(mappedBy = "questionnaire", cascade = { CascadeType.PERSIST })
    private List<Question> fixedQuestions;

    public Questionnaire() {
        this.marketingQuestions = new ArrayList<>();
        this.fixedQuestions = new ArrayList<>();
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
            el.setQuestionnaire(this);
            this.fixedQuestions.add(el);
        });
    }

    public void addMarketingQuestion(Question q) {
        this.marketingQuestions.add(q);
        q.setQuestionnaire(this);
    }

}
