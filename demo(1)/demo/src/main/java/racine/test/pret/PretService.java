package racine.test.pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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



}