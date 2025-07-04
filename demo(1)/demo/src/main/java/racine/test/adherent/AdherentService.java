package racine.test.adherent;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public Optional<Adherent> getAdherentById(Long id) {
        return adherentRepository.findById(id);
    }

    public Adherent saveAdherent(Adherent adherent) {
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

    /**
     * Met à jour le cota d'un adhérent et le sauvegarde en base
     */
    public Adherent updateCota(Long adherentId, int nouveauCota) {
        Optional<Adherent> adherentOpt = adherentRepository.findById(adherentId);
        if (adherentOpt.isPresent()) {
            Adherent adherent = adherentOpt.get();
            adherent.setCota(nouveauCota);
            return adherentRepository.save(adherent);
        }
        throw new RuntimeException("Adhérent non trouvé avec l'ID: " + adherentId);
    }

    /**
     * Incrémente le cota d'un adhérent
     */
    public Adherent incrementerCota(Long adherentId) {
        Optional<Adherent> adherentOpt = adherentRepository.findById(adherentId);
        if (adherentOpt.isPresent()) {
            Adherent adherent = adherentOpt.get();
            adherent.setCota(adherent.getCota() + 1);
            return adherentRepository.save(adherent);
        }
        throw new RuntimeException("Adhérent non trouvé avec l'ID: " + adherentId);
    }

    /**
     * Décrémente le cota d'un adhérent
     */
    public Adherent decrementerCota(Long adherentId) {
        Optional<Adherent> adherentOpt = adherentRepository.findById(adherentId);
        if (adherentOpt.isPresent()) {
            Adherent adherent = adherentOpt.get();
            int nouveauCota = Math.max(0, adherent.getCota() - 1); // Évite les valeurs négatives
            adherent.setCota(nouveauCota);
            return adherentRepository.save(adherent);
        }
        throw new RuntimeException("Adhérent non trouvé avec l'ID: " + adherentId);
    }
}