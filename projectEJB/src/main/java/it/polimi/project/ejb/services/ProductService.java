package it.polimi.project.ejb.services;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.User;
import it.polimi.project.ejb.exceptions.CredentialsException;
import it.polimi.project.ejb.exceptions.UpdateProfileException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.sql.Date;
import java.util.List;

import static org.eclipse.persistence.expressions.ExpressionOperator.today;

@Stateless
public class ProductService {
    @PersistenceContext(name = "DB2Project")
    private EntityManager em;

    public ProductService() {}

    public boolean saveNewProduct(Product p) {
        try {
            em.persist(p);
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public void updateProductOfTheDay(long id) throws UpdateProfileException {
        try {
            long millis = System.currentTimeMillis();
            Date today = new Date(millis);
            Product p = em.find(Product.class, id);
            p.setProductOfTheDay(today);
            em.persist(p);
            //Product dayProd = em.createNamedQuery("Product.findProductOfTheDay", Product.class).getSingleResult();
            //dayProd.setProductOfTheDay(today() - 1);
        } catch (PersistenceException e) {
            throw new UpdateProfileException("Could not change profile");
        }
    }

    public Product getProductOfDay() {
        long millis = System.currentTimeMillis();
        Date today = new Date(millis);
        Product product = null;
        try {
            product = em.createQuery(
                    "SELECT p FROM Product p WHERE p.productOfTheDay = ?1",
                    Product.class)
                    .setParameter(1, today)
                    .getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }
}
