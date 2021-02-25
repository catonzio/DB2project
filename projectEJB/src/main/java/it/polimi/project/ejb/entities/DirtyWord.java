package it.polimi.project.ejb.entities;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NamedQuery(name="DirtyWord.FindAllWords", query = "SELECT b.word FROM DirtyWord b")
public class DirtyWord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String word;
    private String language;

}
