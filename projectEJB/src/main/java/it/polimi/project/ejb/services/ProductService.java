package it.polimi.project.ejb.services;

import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.exceptions.UpdateProfileException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
            LocalDate today = LocalDate.now();
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
        LocalDate today = LocalDate.now();
        Product product = null;
        try {
            product = em.createQuery("SELECT p FROM Product p WHERE p.productOfTheDay = ?1", Product.class)
                    .setParameter(1, today)
                    .getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            products = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public Product findProductForDate(LocalDate date) {
        Product product = null;
        try {
            product = (Product) em.createQuery("SELECT p FROM Product p WHERE p.productOfTheDay = ?1")
                    .setParameter(1, date)
                    .getSingleResult();
        }catch (Exception ex) {
            ex.printStackTrace();
            product = null;
        }
        return product;
    }
}
