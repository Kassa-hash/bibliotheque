package racine.test.exemplaire;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplaireService {

    private final ExemplaireRepository exemplaireRepository;

    public ExemplaireService(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }

    public List<Exemplaire> getAllExemplaire()
    {
        return exemplaireRepository.findAll();
    }

    public Optional<Exemplaire> getExemplairebyId(Long id) {
        return exemplaireRepository.findById(id);
    }
}
