package it.polimi.project.ejb.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
//@NamedQuery(name="Question.getFixedQuestions", query = "SELECT q FROM Question q WHERE q.type LIKE 'user'")
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //type = {user, product}
    private String type;

    private String description;

    private String answer;

    @ManyToOne
    private Questionnaire questionnaire;

}
