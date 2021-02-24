package it.polimi.project.ejb.services;

import it.polimi.project.ejb.entities.UserAnswer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserAnswerService {

    @PersistenceContext(name = "DB2Project")
    private EntityManager em;

    public boolean saveUserAnswer(UserAnswer userAnswer) {
        try {
            em.persist(userAnswer);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
