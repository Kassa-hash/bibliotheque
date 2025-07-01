package racine.test.adherent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class AdherentController {

    private final AdherentService adherentService;
    private final TypeAdherentService typeAdherentService;

    public AdherentController(AdherentService adherentService,TypeAdherentService typeAdherentService) {
        this.adherentService = adherentService;
        this.typeAdherentService=typeAdherentService;
    }

    @GetMapping("/membres")
    public  String showAdherents(Model model)
    {
        List<Adherent> adherents=adherentService.getAllAdherents();
        model.addAttribute("adherents" , adherents);
        return "membres";
    }

    @GetMapping("/membres/nouveau")
    public  String showFormAdherents(Model model)
    {
        List<TypeAdherent> typeAdherents=typeAdherentService.getAllTypeAdherents();
        model.addAttribute("typesAdherent" , typeAdherents);
        model.addAttribute("adherent", new Adherent());
        return "inscriptionAdherent";
    }

    @PostMapping("membres/enregistrer")
    public String saveAdherent(@ModelAttribute("adherent") Adherent adherent,
                               Model model) {

        if (adherent.getNom() == null || adherent.getNom().isEmpty()) {
            model.addAttribute("error", "Le nom est obligatoire");
            model.addAttribute("typesAdherent", typeAdherentService.getAllTypeAdherents());
            return "inscriptionAdherent";
        }

        adherentService.saveAdherent(adherent);
        return "redirect:/membres";
    }
}
