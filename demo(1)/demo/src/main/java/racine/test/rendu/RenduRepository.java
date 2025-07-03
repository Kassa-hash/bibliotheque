package racine.test.rendu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenduRepository extends JpaRepository<Rendu, Long> {

}
