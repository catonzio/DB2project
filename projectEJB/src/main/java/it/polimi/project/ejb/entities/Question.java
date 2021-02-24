package it.polimi.project.ejb.entities;

import it.polimi.project.ejb.enums.QuestionType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NamedQuery(name="Question.findMQByQuestionnaireId", query = "SELECT q FROM Question q WHERE q.questionnaire = ?1 AND q.type = 'MARKETING'")
@NamedQuery(name="Question.findFQByQuestionnaireId", query = "SELECT q FROM Question q WHERE q.questionnaire = ?1 AND q.type = 'FIXED'")
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private String description;

    @ManyToOne()
    private Questionnaire questionnaire;

}
