package racine.test.rendu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import racine.test.adherent.Adherent;
import racine.test.adherent.AdherentService;
import racine.test.penalite.Penalite;
import racine.test.penalite.PenaliteService;
import racine.test.pret.Pret;
import racine.test.pret.PretRepository;
import racine.test.prolongement.Prolongement;
import racine.test.prolongement.ProlongementService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pret")
public class RenduController {

    private final RenduService renduService;
    private final PretRepository pretRepository;
    private final PenaliteService penaliteService;
    private final ProlongementService prolongementService;
    private final AdherentService adherentService;

    @Autowired
    public RenduController(RenduService renduService, PretRepository pretRepository, PenaliteService penaliteService, ProlongementService prolongementService, AdherentService adherentService) {
        this.renduService = renduService;
        this.pretRepository = pretRepository;
        this.penaliteService = penaliteService;
        this.prolongementService = prolongementService;
        this.adherentService = adherentService;
    }

    @GetMapping("/retour/{id}")
    public String saveRendu(
            @PathVariable("id") Long idPret,
            @RequestParam("dateRendu") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateRendu,
            RedirectAttributes redirectAttributes
    ) {
        Pret pret = pretRepository.findById(idPret).orElse(null);
        Adherent adherent=pret.getAdherent();

        if (pret == null) {
            redirectAttributes.addFlashAttribute("error", "Prêt non trouvé pour l'ID : " + idPret);
            return "redirect:/prets";
        }

        int cotaActuel = adherent.getCota();

        Rendu rendu = new Rendu();
        rendu.setPret(pret);
        rendu.setDateRendu((dateRendu));

        LocalDate daterendu2 = dateRendu.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        List<Prolongement> prolongements=prolongementService.getProlongementsByPret(pret.getIdPret());

        for (int i = 0; i <prolongements.size() ; i++) {
            if (prolongements.get(i).getNouvelleDate().isBefore(daterendu2)){
                Penalite penalite=new Penalite();
                penalite.setAdherent(adherent);
                penalite.setDateFin(daterendu2.plusDays(14));
                penaliteService.createPenalite(penalite);
            }
        }

        if (dateRendu.after(pret.getDateLimite()))
        {
            Penalite penalite=new Penalite();
            penalite.setAdherent(adherent);
            penalite.setDateFin(daterendu2.plusDays(14));
            penaliteService.createPenalite(penalite);
        }

        renduService.createRendu(rendu);
        adherentService.updateCota(adherent.getId(),cotaActuel-1);
        redirectAttributes.addFlashAttribute("success", "Rendu enregistré avec succès pour le prêt #" + idPret);
        return "redirect:/prets"; // Rediriger vers la page liste après le rendu
    }
}
