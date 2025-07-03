package racine.test.prolongement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import racine.test.pret.Pret;
import racine.test.pret.PretRepository;
import racine.test.rendu.Rendu;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ProlongementController {

    private final ProlongementService prolongementService;
    private final PretRepository pretRepository;

    @Autowired
    public ProlongementController(ProlongementService prolongementService,PretRepository pretRepository) {
        this.prolongementService = prolongementService;
        this.pretRepository=pretRepository;
    }

    @GetMapping("/nouveauProlongement/{id}")
    public String saveRendu(
            @PathVariable("id") Long idPret,
            @RequestParam("dateRendu") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRendu,
            RedirectAttributes redirectAttributes
    ) {
        Pret pret = pretRepository.findById(idPret).orElse(null);

        if (pret == null) {
            redirectAttributes.addFlashAttribute("error", "Prêt non trouvé pour l'ID : " + idPret);
            return "redirect:/prets";
        }

       Prolongement prolongement=new Prolongement();
        prolongement.setPret(pret);
        prolongement.setNouvelleDate(dateRendu);

        prolongementService.createProlongement(prolongement);
        redirectAttributes.addFlashAttribute("success", "Rendu enregistré avec succès pour le prêt #" + idPret);
        return "redirect:/prets"; // Rediriger vers la page liste après le rendu
    }

    // Récupérer tous les prolongements
//    @GetMapping
//    public List<Prolongement> getAllProlongements() {
//        return prolongementService.getAllProlongements();
//    }

    // Récupérer un prolongement par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Prolongement> getProlongementById(@PathVariable Long id) {
        Optional<Prolongement> prolongement = prolongementService.getProlongementById(id);
        return prolongement.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    // Récupérer les prolongements d'un prêt
//    @GetMapping("/pret/{idPret}")
//    public List<Prolongement> getProlongementsByPret(@PathVariable Long idPret) {
//        return prolongementService.getProlongementsByPret(idPret);
//    }

//    // Mettre à jour un prolongement
//    @PutMapping("/{id}")
//    public ResponseEntity<Prolongement> updateProlongement(@PathVariable Long id, @RequestBody Prolongement prolongementDetails) {
//        Prolongement updatedProlongement = prolongementService.updateProlongement(id, prolongementDetails);
//        return updatedProlongement != null ?
//                ResponseEntity.ok(updatedProlongement) :
//                ResponseEntity.notFound().build();
//    }
    // Supprimer un prolongement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProlongement(@PathVariable Long id) {
        prolongementService.deleteProlongement(id);
        return ResponseEntity.noContent().build();
    }
}
