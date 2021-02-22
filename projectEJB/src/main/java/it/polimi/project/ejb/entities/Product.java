package it.polimi.project.ejb.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Lob
    private byte[] photoimage;

    @Temporal(TemporalType.DATE)
    @Column(unique = true)
    private Date productOfTheDay;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private List<Questionnaire> questionnaires;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    public String getBase64Img() {
        return Base64.getEncoder().encodeToString(this.photoimage);
    }


}
