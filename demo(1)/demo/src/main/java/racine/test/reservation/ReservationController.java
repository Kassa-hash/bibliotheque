package racine.test.reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import racine.test.adherent.Adherent;
import racine.test.adherent.AdherentService;
import racine.test.exemplaire.Exemplaire;
import racine.test.exemplaire.ExemplaireService;
import racine.test.livre.Livre;
import racine.test.livre.LivreService;
import racine.test.penalite.Penalite;
import racine.test.penalite.PenaliteService;
import racine.test.pret.Pret;
import racine.test.pret.PretService;
import racine.test.pret.TypePret;
import racine.test.pret.TypePretService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PretService pretService;

    @Autowired
    private TypePretService typePretService;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private LivreService livreService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private PenaliteService penaliteService;

    @GetMapping("/reservation")
    public String getAllReservations(Model model) {

        List<Reservation> reservations=reservationService.getAllReservations();
        List<TypePret> typePrets=typePretService.getAllTypePrets();
        model.addAttribute("reservations",reservations);
        model.addAttribute("typePrets",typePrets);
        return  "reservation";
    }

    @GetMapping("/reserverLivre")
    public String showFormReservation(Model model)
    {
        model.addAttribute("reservation",new Reservation());
        List<Adherent> adherents = adherentService.getAllAdherents();
        List<Exemplaire> exemplaires=exemplaireService.getAllExemplaire();
        model.addAttribute("adherents", adherents);
        model.addAttribute("exemplaires",exemplaires);
        return "formReservation";
    }

    @PostMapping("/reserverLivre")
    public String submitReservation(@RequestParam("adherent") Long adherentId,
                                    @RequestParam("exemplaire") Long exemplaireId,
                                    @RequestParam("dateDemande") LocalDate dateDemande,
                                    @RequestParam("datePret") LocalDate datePret,
                                   RedirectAttributes redirectAttributes
    )


    {

        // Récupérer les entités avec gestion des Optional
        Optional<Adherent> adherentOpt = adherentService. getAdherentById(adherentId);
        Optional<Exemplaire> exemplaireOpt = exemplaireService.getExemplairebyId(exemplaireId);

        // Vérifier que les entités existent
        if (adherentOpt.isEmpty() || exemplaireOpt.isEmpty()) {
            // Gérer l'erreur - rediriger avec un message d'erreur
            return "redirect:/";
        }

        List<Penalite> penalites = penaliteService.getAllPenalites();

        for (Penalite p : penalites) {
            if (p.getAdherent().getId() == adherentId) {
                if (LocalDate.now().isBefore(p.getDateFin())) {
                    redirectAttributes.addFlashAttribute("errorMessage", "Adhérent encore pénalisé");
                    return "redirect:/reservation";
                }
            }
        }

        Reservation reservation = new Reservation();
        reservation.setAdherent(adherentOpt.get());
        reservation.setExemplaire(exemplaireOpt.get());
        reservation.setDateDemande(dateDemande);
        reservation.setDatePret(datePret);

        reservationService.saveReservation(reservation);
        return "redirect:/reservation";
    }

}

