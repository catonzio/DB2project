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

    private String name;

    private String password;

    private String surname;

    private String username;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}