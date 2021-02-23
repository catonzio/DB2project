package it.polimi.project.ejb.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data

public class Questionnaire implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    @OneToMany(mappedBy = "questionnaire")
    private List<Question> marketingQuestions;

    @OneToMany(mappedBy = "questionnaire")
    private List<Question> fixedQuestions;

    public Questionnaire() {
        this.marketingQuestions = new ArrayList<>();
        this.fixedQuestions = new ArrayList<>();
    }

    public void addMarketingQuestion(Question q) {
        this.marketingQuestions.add(q);
        q.setQuestionnaire(this);
    }

}
