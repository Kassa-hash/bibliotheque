package racine.test.prolongement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProlongementService {

    private final ProlongementRepository prolongementRepository;

    @Autowired
    public ProlongementService(ProlongementRepository prolongementRepository) {
        this.prolongementRepository = prolongementRepository;
    }

    // Créer un prolongement
    public Prolongement createProlongement(Prolongement prolongement) {
        return prolongementRepository.save(prolongement);
    }

    // Récupérer tous les prolongements
    public List<Prolongement> getAllProlongements() {
        return prolongementRepository.findAll();
    }

    // Récupérer un prolongement par son ID
    public Optional<Prolongement> getProlongementById(Long id) {
        return prolongementRepository.findById(id);
    }

//    Récupérer les prolongements d'un prêt
    public List<Prolongement> getProlongementsByPret(Long idPret) {
        return prolongementRepository.findByIdPret(idPret);
    }

//    // Mettre à jour un prolongement
//    public Prolongement updateProlongement(Long id, Prolongement prolongementDetails) {
//        Optional<Prolongement> optionalProlongement = prolongementRepository.findById(id);
//        if (optionalProlongement.isPresent()) {
//            Prolongement existingProlongement = optionalProlongement.get();
//            existingProlongement.setIdPret(prolongementDetails.getIdPret());
//            existingProlongement.setNouvelleDate(prolongementDetails.getNouvelleDate());
//            return prolongementRepository.save(existingProlongement);
//        }
//        return null;
//    }

    // Supprimer un prolongement
    public void deleteProlongement(Long id) {
        prolongementRepository.deleteById(id);
    }
}