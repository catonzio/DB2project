package it.polimi.project.ejb.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usertable", schema = "db2project")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;
    private String email;
}
