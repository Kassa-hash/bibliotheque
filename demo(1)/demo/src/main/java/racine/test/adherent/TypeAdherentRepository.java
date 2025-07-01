package racine.test.adherent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeAdherentRepository extends JpaRepository<TypeAdherent, Long> {
    TypeAdherent findByLibelle(String libelle);
}