package racine.test.pret;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import racine.test.adherent.Adherent;

import java.util.List;

public interface PretRepository extends JpaRepository<Pret, Long> {
    List<Pret> findByAdherentAndDateLimiteIsNull(Adherent adherent);
    int countByAdherentAndDateLimiteIsNull(Adherent adherent);
}
