package racine.test.demo;

import racine.test.demo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Méthodes personnalisées possibles
    User findByEmail(String email);
}
