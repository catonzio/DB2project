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
        return em.createNamedQuery("Questionnaire.findByProdId", Questionnaire.class).setParameter(1, p).setHint("javax.persistence.cache.storeMode", "REFRESH").getSingleResult();
    }

    public void refreshQuestionnaire(Questionnaire q){
        em.refresh(q);
    }

    public void mergeQuestionnaire(Questionnaire questionnaire) {
        em.merge(questionnaire);
    }

    public List<Questionnaire> findAllQuestionnaires() {
        try {
            return em.createNamedQuery("Questionnaire.findAllQuestionnaires", Questionnaire.class).setHint("javax.persistence.cache.storeMode", "REFRESH").getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void persistQuestionnaire(Questionnaire questionnaire) {
        em.persist(questionnaire);
        em.flush();
    }

    public Questionnaire findQuestionnaireById(int questId) {
        try {
            return (Questionnaire) em.createQuery("SELECT q FROM Questionnaire q WHERE q.id = ?1").setParameter(1, questId).setHint("javax.persistence.cache.storeMode", "REFRESH").getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
