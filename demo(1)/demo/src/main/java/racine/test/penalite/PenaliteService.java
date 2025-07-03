package racine.test.penalite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenaliteService {

    @Autowired
    private PenaliteRepository penaliteRepository;

    public List<Penalite> getAllPenalites() {
        return penaliteRepository.findAll();
    }

    public Optional<Penalite> getPenaliteById(Long id) {
        return penaliteRepository.findById(id);
    }

    public Penalite createPenalite(Penalite penalite) {
        return penaliteRepository.save(penalite);
    }

//    public Penalite updatePenalite(Long id, Penalite penaliteDetails) {
//        Penalite penalite = penaliteRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Penalite not found with id " + id));
//
//        penalite.setIdAdherent(penaliteDetails.getIdAdherent());
//        penalite.setIdPret(penaliteDetails.getIdPret());
//        penalite.setDateFin(penaliteDetails.getDateFin());
//
//        return penaliteRepository.save(penalite);
//    }

    public void deletePenalite(Long id) {
        penaliteRepository.deleteById(id);
    }
}

