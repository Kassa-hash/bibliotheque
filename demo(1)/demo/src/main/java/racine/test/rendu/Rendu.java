package racine.test.rendu;

import jakarta.persistence.*;
import racine.test.exemplaire.Exemplaire;
import racine.test.pret.Pret;

import java.util.Date;

@Entity
@Table(name = "Rendu")
public class Rendu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRendu")
    private Long idRendu;

    @Column(name = "dateRendu", nullable = false)
    private Date dateRendu;

    @ManyToOne
    @JoinColumn(name = "idPret", nullable = false)
    private Pret pret;

    public Long getIdRendu() {
        return idRendu;
    }

    public void setIdRendu(Long idRendu) {
        this.idRendu = idRendu;
    }

    public Date getDateRendu() {
        return dateRendu;
    }

    public void setDateRendu(Date dateRendu) {
        this.dateRendu = dateRendu;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }
}
