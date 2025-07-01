package racine.test.livre;

import org.springframework.data.jpa.repository.JpaRepository;
import racine.test.admin.Admin;

public interface LivreRepository  extends JpaRepository<Livre,Long> {
}
