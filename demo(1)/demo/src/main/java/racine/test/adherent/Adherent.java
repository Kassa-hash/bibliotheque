package racine.test.adherent;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "adherent")
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "fin_adhesion")
    private LocalDate finAdhesion;

    @ManyToOne
    @JoinColumn(name = "id_type_adherent", nullable = false)
    private TypeAdherent typeAdherent;

    // Suppression de @Transient pour persister le cota en base
    @Column(name = "cota", nullable = false)
    private int cota = 0; // Valeur par défaut

    @Column(name = "dateDeNaissance", nullable = true)
    private LocalDate dateDeNaissance;

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public int getCota() {
        return cota;
    }

    public void setCota(int cota) {
        this.cota = cota;
    }

    // Constructors
    public Adherent(){
    }

    public Adherent(String nom, String prenom, String email, LocalDate finAdhesion, TypeAdherent typeAdherent) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.finAdhesion = finAdhesion;
        this.typeAdherent = typeAdherent;
        this.cota = 0; // Initialisation par défaut
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFinAdhesion() {
        return finAdhesion;
    }

    public void setFinAdhesion(LocalDate finAdhesion) {
        this.finAdhesion = finAdhesion;
    }

    public TypeAdherent getTypeAdherent() {
        return typeAdherent;
    }

    public void setTypeAdherent(TypeAdherent typeAdherent) {
        this.typeAdherent = typeAdherent;
    }
}