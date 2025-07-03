package racine.test.rendu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import racine.test.pret.Pret;
import racine.test.pret.PretRepository;

import java.util.Date;

@Controller
@RequestMapping("/pret")
public class RenduController {

    private final RenduService renduService;
    private final PretRepository pretRepository;

    @Autowired
    public RenduController(RenduService renduService, PretRepository pretRepository) {
        this.renduService = renduService;
        this.pretRepository = pretRepository;
    }

    @GetMapping("/retour/{id}")
    public String saveRendu(
            @PathVariable("id") Long idPret,
            @RequestParam("dateRendu") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateRendu,
            RedirectAttributes redirectAttributes
    ) {
        Pret pret = pretRepository.findById(idPret).orElse(null);

        if (pret == null) {
            redirectAttributes.addFlashAttribute("error", "Prêt non trouvé pour l'ID : " + idPret);
            return "redirect:/prets";
        }

        Rendu rendu = new Rendu();
        rendu.setPret(pret);
        rendu.setDateRendu(dateRendu);

        renduService.createRendu(rendu);

        redirectAttributes.addFlashAttribute("success", "Rendu enregistré avec succès pour le prêt #" + idPret);
        return "redirect:/prets"; // Rediriger vers la page liste après le rendu
    }
}
