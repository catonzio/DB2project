package it.polimi.project.ejb.services;


import it.polimi.project.ejb.entities.Product;
import it.polimi.project.ejb.entities.Review;
import it.polimi.project.ejb.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Stateless
public class ReviewService {
    @PersistenceContext(name = "DB2Project")
    private EntityManager em;

    public ReviewService() {}

    public void saveReview(Review review) {
        if(review != null) {
            em.persist(review);
            em.flush();
        }
    }

    public void createReview(int productId, int userId, String description){
        LocalDate date = LocalDate.now();
        User user = em.find(User.class, userId);
        Product product = em.find(Product.class, productId);
        Review review = new Review(date, description, user, product);

        user.addReview(review);
        product.addReview(review);

        em.persist(user);
        em.persist(product);
    }

    public Review findReviewByAuthor(User user) {
        try {
            if(user != null) {
                return (Review) em.createQuery("SELECT r FROM Review r WHERE r.user.id = ?1")
                        .setParameter(1, user.getId())
                        .setHint("javax.persistence.cache.storeMode", "REFRESH")
                        .getSingleResult();
            } else
                return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
