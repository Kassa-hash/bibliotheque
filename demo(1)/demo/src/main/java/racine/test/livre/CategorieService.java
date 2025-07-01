package racine.test.livre;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    private final CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }
}
