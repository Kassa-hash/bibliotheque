package racine.test.livre;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @ManyToMany(mappedBy = "categories")
    private Set<Livre> livres;

    public Categorie() {
    }

    public Categorie(String nom) {
        this.nom = nom;
    }

}