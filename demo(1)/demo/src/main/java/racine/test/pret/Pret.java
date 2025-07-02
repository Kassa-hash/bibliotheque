package racine.test.pret;

import jakarta.persistence.*;
import racine.test.adherent.Adherent;
import racine.test.exemplaire.Exemplaire;

import java.util.Date;

@Entity
@Table(name = "Pret")
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPret")
    private Long idPret;

    @ManyToOne
    @JoinColumn(name = "idAdherent", nullable = false)
    private Adherent adherent;

    @Column(name = "datePret", nullable = false)
    private Date datePret;

    @Column(name = "dateLimite", nullable = false)
    private Date dateLimite;

    @ManyToOne
    @JoinColumn(name = "idExemplaire", nullable = false)
    private Exemplaire exemplaire;

    // Constructeurs
    public Pret() {
    }

    public Pret(Adherent adherent, Date datePret, Date dateLimite, Exemplaire exemplaire) {
        this.adherent = adherent;
        this.datePret = datePret;
        this.dateLimite = dateLimite;
        this.exemplaire = exemplaire;
    }

    // Getters et Setters
    public Long getIdPret() {
        return idPret;
    }

    public void setIdPret(Long idPret) {
        this.idPret = idPret;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Date getDatePret() {
        return datePret;
    }

    public void setDatePret(Date datePret) {
        this.datePret = datePret;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Exemplaire getIdExemplaire() {
        return exemplaire;
    }

    public void setIdExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }
}