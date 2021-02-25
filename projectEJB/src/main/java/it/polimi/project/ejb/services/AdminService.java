package it.polimi.project.ejb.services;

import it.polimi.project.ejb.entities.Admin;
import it.polimi.project.ejb.exceptions.CredentialsException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless
public class AdminService {
    @PersistenceContext(name = "DB2Project")
    private EntityManager em;

    public AdminService() {}

    public Admin checkCredentials(String usrn, String pwd) throws CredentialsException, NonUniqueResultException {
        List<Admin> aList = null;
        try {
            aList = em.createNamedQuery("Admin.checkCredentials", Admin.class)
                    .setParameter(1, usrn)
                    .setParameter(2, pwd)
                    .getResultList();
        } catch (PersistenceException e) {
            //throw new CredentialsException("Could not verify credentials");
            e.printStackTrace();
        }
        if (aList.isEmpty())
            return null;
        else if (aList.size() == 1)
            return aList.get(0);
        throw new NonUniqueResultException("More than one user registered with same credentials");

    }


}
