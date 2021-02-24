package it.polimi.project.ejb.services;


import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Question;
import it.polimi.project.ejb.entities.Questionnaire;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class QuestionService {
    @PersistenceContext(name = "DB2Project")
    private EntityManager em;

    public QuestionService() {}

    public List<Question> findMarketingQuestionByQuestionnaireId(Questionnaire questionnaire) {
        return em.createNamedQuery("Question.findMQByQuestionnaireId", Question.class).setParameter(1, questionnaire).getResultList();
    }
    public List<Question> findFixedQuestionByQuestionnaireId(Questionnaire questionnaire) {
        return em.createNamedQuery("Question.findFQByQuestionnaireId", Question.class).setParameter(1, questionnaire).getResultList();
    }
}
