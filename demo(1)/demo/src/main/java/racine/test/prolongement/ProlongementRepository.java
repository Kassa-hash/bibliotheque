package racine.test.prolongement;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProlongementRepository extends JpaRepository<Prolongement, Long> {

}