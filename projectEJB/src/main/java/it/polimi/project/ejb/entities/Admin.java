package it.polimi.project.ejb.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@NamedQuery(name = "Admin.checkCredentials", query = "SELECT r FROM Admin r  WHERE (r.username = ?1 or r.email = ?1) and r.password = ?2")
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

}
