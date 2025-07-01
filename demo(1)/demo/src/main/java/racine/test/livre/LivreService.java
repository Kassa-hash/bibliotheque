package racine.test.livre;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {

    private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }


    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }
}
