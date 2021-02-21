package it.polimi.project.ejb.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the usertable database table.
 *
 */
@Entity
@Data
//@Table(name = "usertable", schema = "db2project")
@NamedQuery(name = "User.checkCredentials", query = "SELECT r FROM User r  WHERE (r.username = ?1 or r.email = ?1) and r.password = ?2")

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Boolean is_blocked;
    private int points;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    public User() {
        is_blocked = true;
    }


}