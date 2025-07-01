package racine.test.adherent;

import jakarta.persistence.*;
import racine.test.adherent.Adherent;

import java.util.List;

@Entity
@Table(name = "type_adherent")
public class TypeAdherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libelle;

    @Column(nullable = false)
    private double cota;

    @Column(nullable = false)
    private double cotisation;

    @OneToMany(mappedBy = "typeAdherent", cascade = CascadeType.ALL)
    private List<Adherent> adherents;

    // Constructors
    public TypeAdherent() {
    }

    public TypeAdherent(String libelle, double cota, double cotisation) {
        this.libelle = libelle;
        this.cota = cota;
        this.cotisation = cotisation;
    }

    // Getters and Setters
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

    public double getCota() {
        return cota;
    }

    public void setCota(double cota) {
        this.cota = cota;
    }

    public double getCotisation() {
        return cotisation;
    }

    public void setCotisation(double cotisation) {
        this.cotisation = cotisation;
    }

    public List<Adherent> getAdherents() {
        return adherents;
    }

    public void setAdherents(List<Adherent> adherents) {
        this.adherents = adherents;
    }
}