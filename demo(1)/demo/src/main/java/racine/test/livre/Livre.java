package racine.test.livre;

import jakarta.persistence.*;
import racine.test.auteur.Auteur;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "livre")
public class Livre {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "auteur_id", nullable = false)
    private Auteur auteur;

    @ManyToOne
    @JoinColumn(name = "typelivre_id")
    private TypeLivre typelivre;

    @ManyToMany
    @JoinTable(
            name = "livre_categorie",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private Set<Categorie> categories;

    @Column(nullable = false)
    private Date dateEdition;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public TypeLivre getTypelivre() {
        return typelivre;
    }

    public void setTypelivre(TypeLivre typelivre) {
        this.typelivre = typelivre;
    }

    public Set<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(Set<Categorie> categories) {
        this.categories = categories;
    }

    public Date getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(Date dateEdition) {
        this.dateEdition = dateEdition;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
