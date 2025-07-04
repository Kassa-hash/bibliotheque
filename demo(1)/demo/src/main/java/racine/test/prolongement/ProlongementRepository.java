package racine.test.prolongement;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProlongementRepository extends JpaRepository<Prolongement, Long> {
    @Query("SELECT p FROM Prolongement p WHERE p.pret.id = :idPret")
    List<Prolongement> findByIdPret(@Param("idPret") Long idPret);
}