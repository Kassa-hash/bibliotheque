package racine.test.adherent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdherentRepository extends JpaRepository<Adherent, Long> {
    List<Adherent> findByNomContainingOrPrenomContaining(String nom, String prenom);
    List<Adherent> findByTypeAdherentId(Long typeAdherentId);
    Adherent findByEmail(String email);
}