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
    private final TypePretService typePretService;



    public PretController(PretService pretService, AdherentService adherentService,
                          LivreService livreService, ExemplaireService exemplaireService, PenaliteService penaliteService, TypePretService typePretService) {
        this.pretService = pretService;
        this.adherentService = adherentService;
        this.livreService = livreService;
        this.exemplaireService = exemplaireService;
        this.penaliteService = penaliteService;
        this.typePretService = typePretService;
    }

    @GetMapping("/prets")
    public String formPret(Model model) {
        model.addAttribute("pret", new Pret());
        List<Adherent> adherents = adherentService.getAllAdherents();
        List<Livre> livres = livreService.getAllLivres();
        List<TypePret> typePrets=typePretService.getAllTypePrets();

        model.addAttribute("adherents", adherents);
        model.addAttribute("livres", livres);
        model.addAttribute("typePrets" , typePrets);
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
                                  @RequestParam Long idTypePret,
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

            Adherent adherent = adherentOpt.get();

            if (adherent.getFinAdhesion().isBefore(LocalDate.now())) {
                redirectAttributes.addFlashAttribute("errorMessage", "Adhésion expirée, cet adhérent doit se réinscrire");
                return "redirect:/prets";
            }

            List<Penalite> penalites = penaliteService.getAllPenalites();

            for (Penalite p : penalites) {
                if (p.getAdherent().getId() == adherent.getId()) {
                    if (LocalDate.now().isBefore(p.getDateFin())) {
                        redirectAttributes.addFlashAttribute("errorMessage", "Adhérent encore pénalisé");
                        return "redirect:/prets";
                    }
                }
            }

            int cotaActuel = adherent.getCota();


            // Vérifier si l'adhérent a atteint son quota
            if (adherent.getCota() >= adherent.getTypeAdherent().getCota()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Vous avez déjà atteint le nombre de livres que vous pouvez emprunter");
                return "redirect:/prets";
            }

            // Récupération de l'exemplaire par livre et numéro
            Optional<Exemplaire> exemplaireOpt = exemplaireService.findByLivreIdAndNumero(idExemplaire, num);
            Optional<TypePret> typePretOpt=typePretService.getTypePretById(idTypePret);
            if (!exemplaireOpt.isPresent()) {
                redirectAttributes.addFlashAttribute("errorMessage", "Exemplaire introuvable");
                return "redirect:/prets";
            }

            Exemplaire exemplaire = exemplaireOpt.get();
            TypePret typePret=typePretOpt.get();


            // Conversion de la date
            LocalDate datePretLocal = LocalDate.parse(datePret);
            Date datePretDate = Date.from(datePretLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());



            // Création du prêt
            Pret pret = new Pret();
            pret.setAdherent(adherent);
            pret.setExemplaire(exemplaire);
            pret.setDatePret(datePretDate);

            pret.setTypepret(typePret);

            if(pret.getTypepret().getId()==1)
            {
                double nbjours =adherent.getTypeAdherent().getCotisation();
               Long nb=Math.round(nbjours);
                LocalDate dateLimiteLocal = datePretLocal.plusDays(nb);
                Date dateLimite = Date.from(dateLimiteLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
                pret.setDateLimite(dateLimite);
            }

            if(pret.getTypepret().getId()==2)
            {
                LocalDate dateLimiteLocal = datePretLocal.plusDays(1);
                Date dateLimite = Date.from(dateLimiteLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
                pret.setDateLimite(dateLimite);
            }

            // Enregistrement du prêt
            Pret pretEnregistre = pretService.savePret(pret);

            // Incrémenter le cota en base de données après l'enregistrement réussi
            adherentService.updateCota(adherent.getId(),cotaActuel+1);

            System.out.println("Nouveau cota pour l'adhérent " + adherent.getNom() + " " + adherent.getPrenom() + ": " + (adherent.getCota() + 1));

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