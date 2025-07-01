package racine.test.livre;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import racine.test.auteur.Auteur;
import racine.test.auteur.AuteurService;

import java.util.List;

@Controller
public class LivreController {

    private final LivreService livreService;
    private final CategorieService categorieService;
    private final TypeLivreService typeLivreService;
    private final AuteurService auteurService; // Ajout du service Auteur

    // Injection des 4 services via le constructeur
    public LivreController(
            LivreService livreService,
            CategorieService categorieService,
            TypeLivreService typeLivreService,
            AuteurService auteurService) { // Ajout du paramètre
        this.livreService = livreService;
        this.categorieService = categorieService;
        this.typeLivreService = typeLivreService;
        this.auteurService = auteurService; // Initialisation
    }

    @GetMapping("/livre")
    public String showLivres(Model model) {
        List<Livre> livres = livreService.getAllLivres();
        model.addAttribute("livres", livres);
        return "livres";
    }

    @GetMapping("/livres/nouveau")
    public String showFormLivres(Model model) {
        List<Categorie> categories = categorieService.getAllCategories();
        List<TypeLivre> typeLivres = typeLivreService.getAllTypeLivre();
        List<Auteur> auteurs = auteurService.getAllAuteur(); // Récupération des auteurs

        model.addAttribute("categories", categories);
        model.addAttribute("types", typeLivres);
        model.addAttribute("auteurs", auteurs); // Ajout des auteurs au modèle
        model.addAttribute("livre", new Livre()); // Objet vide pour le formulaire

        return "FormLivre";
    }

}