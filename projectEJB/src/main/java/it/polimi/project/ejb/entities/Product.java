package it.polimi.project.ejb.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Entity
@Data
@NamedQuery(name = "Product.findProductOfTheDay", query = "SELECT p FROM Product p  WHERE p.productOfTheDay = ?1 ")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Lob
    private byte[] photoimage;

    //@Temporal(TemporalType.DATE)
    @Column(unique = true)
    private LocalDate productOfTheDay;

    @OneToOne(mappedBy = "relatedProduct",fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    private Questionnaire questionnaire;

    @OneToMany(mappedBy = "product", cascade = { CascadeType.REMOVE,
            CascadeType.REFRESH }, fetch = FetchType.EAGER)
    private List<Review> reviews;

    public Product() {
        this.reviews = new ArrayList<>();
    }

    public String getBase64Img() {
        return Base64.getEncoder().encodeToString(this.photoimage);
    }

    public void addReview(Review review) {
        if(review != null) {
            getReviews().add(review);
        }
    }
}
