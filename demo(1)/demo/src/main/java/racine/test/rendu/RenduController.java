package racine.test.rendu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import racine.test.penalite.Penalite;
import racine.test.penalite.PenaliteService;
import racine.test.pret.Pret;
import racine.test.pret.PretRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
@RequestMapping("/pret")
public class RenduController {

    private final RenduService renduService;
    private final PretRepository pretRepository;
    private final PenaliteService penaliteService;

    @Autowired
    public RenduController(RenduService renduService, PretRepository pretRepository, PenaliteService penaliteService) {
        this.renduService = renduService;
        this.pretRepository = pretRepository;
        this.penaliteService = penaliteService;
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
        rendu.setDateRendu((dateRendu));

        LocalDate daterendu2 = dateRendu.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        if (dateRendu.after(pret.getDateLimite()))
        {
            Penalite penalite=new Penalite();
            penalite.setDateFin(daterendu2.plusDays(14));
            penaliteService.createPenalite(penalite);
        }
        renduService.createRendu(rendu);

        redirectAttributes.addFlashAttribute("success", "Rendu enregistré avec succès pour le prêt #" + idPret);
        return "redirect:/prets"; // Rediriger vers la page liste après le rendu
    }
}
