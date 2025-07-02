package racine.test.pret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import racine.test.adherent.Adherent;
import racine.test.adherent.AdherentService;
import racine.test.livre.Livre;
import racine.test.livre.LivreService;

import java.util.List;
import java.util.Optional;

@Controller
public class PretController {

    private final PretService pretService;
    private final AdherentService adherentService;
    private final LivreService livreService;

    public PretController(PretService pretService, AdherentService adherentService, LivreService livreService) {
        this.pretService = pretService;
        this.adherentService = adherentService;
        this.livreService = livreService;
    }

    @GetMapping("/prets")
    public String formPret(Model model) {
        model.addAttribute("pret", new Pret());
       List<Adherent> adherents=adherentService.getAllAdherents();
       List<Livre> livres=livreService.getAllLivres();
       model.addAttribute("adherents",adherents);
       model.addAttribute("livres" , livres);
       return "prets";
    }


}