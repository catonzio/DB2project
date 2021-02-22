package it.polimi.project.ejb.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
<<<<<<< HEAD
@Table(name = "usertable", schema = "db2project")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;
    private String email;
}
=======
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
>>>>>>> eafbab0d2deefda156c02d93418e3ca1fdd548b5
