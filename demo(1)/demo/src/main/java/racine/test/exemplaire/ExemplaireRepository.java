package racine.test.exemplaire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface ExemplaireRepository extends JpaRepository<Exemplaire,Long> {
    @Query("SELECT e FROM Exemplaire e WHERE e.livre.id = :livreId AND e.numero = :numero")
    Optional<Exemplaire> findByLivreIdAndNumero(@Param("livreId") Long livre_id, @Param("numero") Integer numero);
}
