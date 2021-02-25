package it.polimi.project.ejb.services;


import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.entities.Review;
import it.polimi.project.ejb.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class QuestionnaireService {
    @PersistenceContext(name = "DB2Project")
    private EntityManager em;

    public QuestionnaireService() {}

    public boolean saveQuestionnaire(Questionnaire q, Product p) {
        try {
            if(q != null) {
                q.setRelatedProduct(p);
                p.setQuestionnaire(q);
                //em.persist(q);
                return true;
            } else
                return false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public Questionnaire findQuestionnaireByProdId(Product p) {
        return em.createNamedQuery("Questionnaire.findByProdId", Questionnaire.class).setParameter(1, p).getSingleResult();
    }

    public List<Questionnaire> findAllQuestionnaires() {
        try {
            return em.createNamedQuery("Questionnaire.findAllQuestionnaires", Questionnaire.class).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
