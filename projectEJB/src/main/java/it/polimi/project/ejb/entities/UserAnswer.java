package it.polimi.project.ejb.entities;

import it.polimi.project.ejb.enums.QuestionType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NamedQuery(name="UserAnswer.findByQuestionnaire", query = "SELECT a FROM UserAnswer a WHERE a.relatedQuestionnaire = ?1")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int pointsEarned;

    @ElementCollection
    private Map<Question, String> answers;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Questionnaire relatedQuestionnaire;

    @ManyToOne
    private User relatedUser;

    public UserAnswer() {
        this.answers = new HashMap<>();
    }

    public void setRelatedQuestionnaire(Questionnaire relatedQuestionnaire) {
        if(relatedQuestionnaire != null) {
            this.relatedQuestionnaire = relatedQuestionnaire;
            relatedQuestionnaire.addAnswer(this);
        }
    }

    public void setRelatedUser(User relatedUser) {
        if(relatedUser != null) {
            this.relatedUser = relatedUser;
            relatedUser.addAnswer(this);
        }
    }

    public void addAnswer(Question question, String answ) {
        answers.put(question, answ);
    }
}
