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
import racine.test.penalite.Penalite;
import racine.test.penalite.PenaliteService;

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
    private final PenaliteService penaliteService;



    public PretController(PretService pretService, AdherentService adherentService,
                          LivreService livreService, ExemplaireService exemplaireService, PenaliteService penaliteService) {
        this.pretService = pretService;
        this.adherentService = adherentService;
        this.livreService = livreService;
        this.exemplaireService = exemplaireService;
        this.penaliteService = penaliteService;
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

    @GetMapping("/listePret")
    public String listePret(Model model){
        List<Pret> prets=pretService.getAllPrets();
        model.addAttribute("prets",prets);
        return "listePrets";
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
            Adherent adherent = adherentOpt.get();
            if (!adherentOpt.isPresent()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Adhérent introuvable");
                return "redirect:/prets";
            }

            if (adherent.getFinAdhesion().isBefore(LocalDate.now()))
            {
                redirectAttributes.addFlashAttribute("errorMessage", "Adhésion éxpirèe, cet adhérent doit se réinscrire");
                return "redirect:/prets";
            }

            List<Penalite> penalites=penaliteService.getAllPenalites();

            for (Penalite p : penalites) {
                if (p.getAdherent().getId() == adherent.getId()) {
                    if (LocalDate.now().isBefore(p.getDateFin())) {
                        redirectAttributes.addFlashAttribute("errorMessage", "Adhérent encore pénalisé");
                        return "redirect:/prets";
                    }
                }
            }

            if (adherent.getTypeAdherent().getCota()<= adherent.getCota()){
                redirectAttributes.addFlashAttribute("errorMessage", "Vous avez déja atteint le nombre de livre que vous pouvez preter");
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
            pret.setExemplaire(exemplaire);
            pret.setDatePret(datePretDate);
            pret.setDateLimite(dateLimite);

            // Enregistrement du prêt
            Pret pretEnregistre = pretService.savePret(pret);
            int cota=adherent.getCota();
            cota=cota++;
            adherent.setCota(cota);

            redirectAttributes.addFlashAttribute("successMessage", "Prêt enregistré avec succès !");
            return "redirect:/prets";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de l'enregistrement: " + e.getMessage());
            return "redirect:/prets";
        }
    }



    /**
     * Affiche les détails d'un prêt spécifique
     */
    @GetMapping("/pret/details/{id}")
    public String detailsPret(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Pret> pretOpt = pretService.getPretById(id);

        if (!pretOpt.isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Prêt introuvable");
            return "redirect:/listePret";
        }

        model.addAttribute("pret", pretOpt.get());
        return "detailsPret"; // Nom de la vue pour afficher les détails
    }

    /**
     * Annule un prêt (si nécessaire)
     */

}