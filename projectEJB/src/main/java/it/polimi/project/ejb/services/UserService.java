package it.polimi.project.ejb.services;

import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.exceptions.CredentialsException;
import it.polimi.project.ejb.exceptions.UpdateProfileException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

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

    public User checkCredentials(String usrn, String pwd) throws CredentialsException, NonUniqueResultException {
        List<User> uList = null;
        try {
            uList = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, usrn).setParameter(2, pwd)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new CredentialsException("Could not verify credentals");
        }
        if (uList.isEmpty())
            return null;
        else if (uList.size() == 1)
            return uList.get(0);
        throw new NonUniqueResultException("More than one user registered with same credentials");

    }

    public void updateProfile(User u) throws UpdateProfileException {
        try {
            em.merge(u);
        } catch (PersistenceException e) {
            throw new UpdateProfileException("Could not change profile");
        }
    }

    public List findUserByUsrnEmail(String usrn, String email) {
        List users;
        users = em.createQuery("SELECT u FROM User u WHERE u.email = ?1 and u.username = ?2")
                .setParameter(1, email)
                .setParameter(2, usrn)
                .getResultList();
        return users;
    }
}
