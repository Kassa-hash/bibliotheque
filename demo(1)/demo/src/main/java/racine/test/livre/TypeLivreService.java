package racine.test.livre;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeLivreService {

    public TypeLivreService(TypeLivreRepository typeLivreRepository) {

        this.typeLivreRepository = typeLivreRepository;
    }

    private final TypeLivreRepository typeLivreRepository;


    public List<TypeLivre> getAllTypeLivre() {
        return typeLivreRepository.findAll();
    }

}
