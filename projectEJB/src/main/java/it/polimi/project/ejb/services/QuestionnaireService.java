package it.polimi.project.ejb.services;

import it.polimi.project.ejb.entities.Questionnaire;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class QuestionnaireService {

    @PersistenceContext(name = "DB2Project")
    private EntityManager em;

    public boolean saveNewQuestionnaire(Questionnaire q) {
        try {
            em.persist(q);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
