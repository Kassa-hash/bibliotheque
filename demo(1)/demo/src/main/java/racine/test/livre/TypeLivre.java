package racine.test.livre;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "typelivre")
public class TypeLivre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libelle;  // Ex: "Roman", "BD", "Manuel", etc.

    @OneToMany(mappedBy = "typelivre")
    private List<Livre> livres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }
}
