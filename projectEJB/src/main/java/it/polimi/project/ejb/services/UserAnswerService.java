package it.polimi.project.ejb.services;

import it.polimi.project.ejb.entities.Questionnaire;
import it.polimi.project.ejb.entities.UserAnswer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    public boolean updateUserAnswer(UserAnswer userAnswer) {
        try {
            em.merge(userAnswer);
            return true;
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<UserAnswer> findUserAnswersByQuestionnaire(Questionnaire questionnaire) {
        return em.createNamedQuery("UserAnswer.findByQuestionnaire", UserAnswer.class).setParameter(1, questionnaire)
                .getResultList();
    }

    public Boolean checkForBadWords(String answ) {
        List<String> words = em.createNamedQuery("DirtyWord.FindAllWords", String.class).getResultList();
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        String[] s1 = answ.split(" ");

        for(String s : s1){
            words.forEach(w -> {
                if(s.equals(w)) result.set(true);
                });
        }

        return result.get();
    }
}
