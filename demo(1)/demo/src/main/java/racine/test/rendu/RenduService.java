package racine.test.rendu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RenduService {

    private final RenduRepository renduRepository;

    @Autowired
    public RenduService(RenduRepository renduRepository) {
        this.renduRepository = renduRepository;
    }

    public List<Rendu> getAllRendus() {
        return renduRepository.findAll();
    }

    public Optional<Rendu> getRenduById(Long id) {
        return renduRepository.findById(id);
    }

    public Rendu createRendu(Rendu rendu) {
        return renduRepository.save(rendu);
    }

    public void deleteRendu(Long id) {
        renduRepository.deleteById(id);
    }
}
