package racine.test.pret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racine.test.adherent.Adherent;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PretService {

    private final PretRepository pretRepository;

    @Autowired
    public PretService(PretRepository pretRepository) {
        this.pretRepository = pretRepository;
    }

    public List<Pret> getAllPrets() {
        return pretRepository.findAll();
    }

    public Optional<Pret> getPretById(Long id) {
        return pretRepository.findById(id);
    }

    public Pret savePret(Pret pret) {
        return pretRepository.save(pret);
    }

    public void deletePret(Long id) {
        pretRepository.deleteById(id);
    }

    public Pret updatePret(Pret pret) {
        return pretRepository.save(pret);
    }

    /**
     * Récupère tous les prêts en cours pour un adhérent donné
     * (prêts qui n'ont pas encore été retournés)
     * @param adherent L'adhérent pour lequel récupérer les prêts
     * @return Liste des prêts en cours pour cet adhérent
     */
    public List<Pret> getPretsByAdherent(Adherent adherent) {
        return pretRepository.findAll().stream()
                .filter(pret -> pret.getAdherent().getId().equals(adherent.getId()))
                .filter(pret -> pret.getDateLimite() == null) // Prêts non retournés
                .collect(Collectors.toList());
    }

    /**
     * Compte le nombre de prêts en cours pour un adhérent donné
     * @param adherent L'adhérent pour lequel compter les prêts
     * @return Le nombre de prêts en cours
     */
    public int countPretsByAdherent(Adherent adherent) {
        return getPretsByAdherent(adherent).size();
    }

    /**
     * Récupère tous les prêts (retournés et non retournés) pour un adhérent donné
     * @param adherent L'adhérent pour lequel récupérer tous les prêts
     * @return Liste de tous les prêts pour cet adhérent
     */
    public List<Pret> getAllPretsByAdherent(Adherent adherent) {
        return pretRepository.findAll().stream()
                .filter(pret -> pret.getAdherent().getId().equals(adherent.getId()))
                .collect(Collectors.toList());
    }
}