package racine.test.pret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypePretService {

    private final TypePretRepository typePretRepository;

    @Autowired
    public TypePretService(TypePretRepository typePretRepository) {
        this.typePretRepository = typePretRepository;
    }

    // Créer un nouveau TypePret
    public TypePret createTypePret(TypePret typePret) {
        return typePretRepository.save(typePret);
    }

    // Récupérer tous les TypePret
    public List<TypePret> getAllTypePrets() {
        return typePretRepository.findAll();
    }

    // Récupérer un TypePret par son id
    public Optional<TypePret> getTypePretById(Long id) {
        return typePretRepository.findById(id);
    }

    // Mettre à jour un TypePret
    public TypePret updateTypePret(Long id, TypePret typePretDetails) {
        Optional<TypePret> optionalTypePret = typePretRepository.findById(id);
        if (optionalTypePret.isPresent()) {
            TypePret existingTypePret = optionalTypePret.get();
            existingTypePret.setLibelle(typePretDetails.getLibelle());
            return typePretRepository.save(existingTypePret);
        }
        return null;
    }

    // Supprimer un TypePret
    public void deleteTypePret(Long id) {
        typePretRepository.deleteById(id);
    }
}