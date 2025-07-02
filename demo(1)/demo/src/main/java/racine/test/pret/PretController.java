package racine.test.pret;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import racine.test.adherent.Adherent;
import racine.test.adherent.AdherentService;
import racine.test.livre.Livre;
import racine.test.livre.LivreService;
import racine.test.exemplaire.Exemplaire;
import racine.test.exemplaire.ExemplaireService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PretController {

    private final PretService pretService;
    private final AdherentService adherentService;
    private final LivreService livreService;
    private final ExemplaireService exemplaireService;

    public PretController(PretService pretService, AdherentService adherentService,
                          LivreService livreService, ExemplaireService exemplaireService) {
        this.pretService = pretService;
        this.adherentService = adherentService;
        this.livreService = livreService;
        this.exemplaireService = exemplaireService;
    }

    @GetMapping("/prets")
    public String formPret(Model model) {
        model.addAttribute("pret", new Pret());
        List<Adherent> adherents = adherentService.getAllAdherents();
        List<Livre> livres = livreService.getAllLivres();
        model.addAttribute("adherents", adherents);
        model.addAttribute("livres", livres);
        return "prets";
    }

    @PostMapping("/prets/nouveau")
    public String enregistrerPret(@RequestParam Long idAdherent,
                                  @RequestParam Long idExemplaire,
                                  @RequestParam String datePret,
                                  @RequestParam Integer num,
                                  RedirectAttributes redirectAttributes) {

        try {
            // Validation des données
            if (idAdherent == null || idExemplaire == null || datePret == null || num == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Tous les champs sont obligatoires");
                return "redirect:/prets";
            }

            // Récupération de l'adhérent
            Optional<Adherent> adherentOpt = adherentService.getAdherentById(idAdherent);
            if (!adherentOpt.isPresent()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Adhérent introuvable");
                return "redirect:/prets";
            }

            // Récupération de l'exemplaire par livre et numéro
            Optional<Exemplaire> exemplaireOpt = exemplaireService.findByLivreIdAndNumero(idExemplaire, num);
            if (!exemplaireOpt.isPresent()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Exemplaire introuvable");
                return "redirect:/prets";
            }

            Exemplaire exemplaire = exemplaireOpt.get();

            // Conversion de la date
            LocalDate datePretLocal = LocalDate.parse(datePret);
            Date datePretDate = Date.from(datePretLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Calcul de la date limite (exemple: 14 jours après le prêt)
            LocalDate dateLimiteLocal = datePretLocal.plusDays(14);
            Date dateLimite = Date.from(dateLimiteLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

            // Création du prêt
            Pret pret = new Pret();
            pret.setAdherent(adherentOpt.get());
            pret.setIdExemplaire(exemplaire);
            pret.setDatePret(datePretDate);
            pret.setDateLimite(dateLimite);

            // Enregistrement du prêt
            Pret pretEnregistre = pretService.savePret(pret);

            redirectAttributes.addFlashAttribute("successMessage", "Prêt enregistré avec succès !");
            return "redirect:/prets";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de l'enregistrement: " + e.getMessage());
            return "redirect:/prets";
        }
    }
}