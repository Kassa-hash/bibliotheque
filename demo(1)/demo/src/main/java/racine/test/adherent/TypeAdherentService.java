package racine.test.adherent;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeAdherentService {
    private final TypeAdherentRepository typeAdherentRepository;

    public TypeAdherentService(TypeAdherentRepository typeAdherentRepository) {
        this.typeAdherentRepository = typeAdherentRepository;
    }

    public List<TypeAdherent> getAllTypeAdherents() {
        return typeAdherentRepository.findAll();
    }

    public TypeAdherent getTypeAdherentById(Long id) {
        return typeAdherentRepository.findById(id).orElse(null);
    }

    public TypeAdherent saveTypeAdherent(TypeAdherent typeAdherent) {
        return typeAdherentRepository.save(typeAdherent);
    }

    public void deleteTypeAdherent(Long id) {
        typeAdherentRepository.deleteById(id);
    }

    public TypeAdherent findByLibelle(String libelle) {
        return typeAdherentRepository.findByLibelle(libelle);
    }
}