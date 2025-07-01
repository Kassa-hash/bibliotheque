package racine.test.adherent;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdherentService {
    private final AdherentRepository adherentRepository;
    private final TypeAdherentService typeAdherentService;

    public AdherentService(AdherentRepository adherentRepository, TypeAdherentService typeAdherentService) {
        this.adherentRepository = adherentRepository;
        this.typeAdherentService = typeAdherentService;
    }

    public List<Adherent> getAllAdherents() {
        return adherentRepository.findAll();
    }

    public Adherent getAdherentById(Long id) {
        return adherentRepository.findById(id).orElse(null);
    }

    public Adherent saveAdherent(Adherent adherent) {
        // You might want to add validation here
        return adherentRepository.save(adherent);
    }

    public void deleteAdherent(Long id) {
        adherentRepository.deleteById(id);
    }

    public List<Adherent> searchAdherents(String searchTerm) {
        return adherentRepository.findByNomContainingOrPrenomContaining(searchTerm, searchTerm);
    }

    public List<Adherent> getAdherentsByType(Long typeAdherentId) {
        return adherentRepository.findByTypeAdherentId(typeAdherentId);
    }

    public Adherent getAdherentByEmail(String email) {
        return adherentRepository.findByEmail(email);
    }


}