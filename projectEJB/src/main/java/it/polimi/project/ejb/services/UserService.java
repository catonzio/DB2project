package it.polimi.project.ejb.services;

import it.polimi.project.ejb.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {

    @PersistenceContext(name = "DB2Project")
    private EntityManager em;

    public UserService() {}

    public boolean saveUser(User u) {
        try {
            em.persist(u);
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
