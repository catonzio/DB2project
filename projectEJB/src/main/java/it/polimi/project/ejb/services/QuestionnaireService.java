package it.polimi.project.ejb.services;


import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.entities.Review;
import it.polimi.project.ejb.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Stateless
public class QuestionnaireService {
    @PersistenceContext(name = "DB2Project")
    private EntityManager em;

    public QuestionnaireService() {}

    public boolean saveQuestionnaire(Questionnaire q) {
        try {
            if(q != null) {
                em.persist(q);
                return true;
            } else
                return false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
