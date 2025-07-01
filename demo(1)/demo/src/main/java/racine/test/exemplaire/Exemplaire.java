package racine.test.exemplaire;

import jakarta.persistence.*;
import racine.test.livre.Livre;
import racine.test.livre.TypeLivre;

@Entity
@Table(name = "exemplaire")
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "livre_id")
    private Livre livre;
}
