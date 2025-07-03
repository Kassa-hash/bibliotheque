package racine.test.reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import racine.test.adherent.Adherent;
import racine.test.adherent.AdherentService;
import racine.test.livre.Livre;
import racine.test.livre.LivreService;
import racine.test.pret.Pret;
import racine.test.pret.PretService;

import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private PretService pretService;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private LivreService livreService;

    @GetMapping("/reservation")
    public String getAllReservations(Model model) {
        List<Reservation> reservations=reservationService.getAllReservations();
        model.addAttribute("reservations",reservations);
        return  "reservation";
    }

    @GetMapping("/reserverLivre")
    public String showFormReservation(Model model)
    {
        model.addAttribute("reservation",new Reservation());
        List<Adherent> adherents = adherentService.getAllAdherents();
        List<Livre> livres = livreService.getAllLivres();
        model.addAttribute("adherents", adherents);
        model.addAttribute("livres", livres);
        return "formReservation";
    }

    @PostMapping("/reserverLivre")
    public String submitReservation(@ModelAttribute Reservation reservation) {
        // Enregistrer la réservation
        reservationService.saveReservation(reservation);
        return "redirect:/reservations";
    }

}

