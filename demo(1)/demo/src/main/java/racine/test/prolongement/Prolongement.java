package racine.test.prolongement;

import jakarta.persistence.*;
import racine.test.pret.Pret;

import java.time.LocalDate;

@Entity
@Table(name = "prolongement")
public class Prolongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPret", nullable = false)
    private Pret pret;

    @Column(name = "nouvelleDate", nullable = false)
    private LocalDate nouvelleDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    public LocalDate getNouvelleDate() {
        return nouvelleDate;
    }

    public void setNouvelleDate(LocalDate nouvelleDate) {
        this.nouvelleDate = nouvelleDate;
    }
}