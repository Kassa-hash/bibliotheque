package racine.test.admin;
import jakarta.persistence.*;

@Entity
@Table(name = "racine/test/admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String prenom;

    @Column(nullable = false)
    private String mdp;

   public Admin() {}

    public Admin (String nom, String prenom,String mdp)
    {
        this.nom=nom;
        this.prenom=prenom;
        this.mdp=mdp;
    }

}
