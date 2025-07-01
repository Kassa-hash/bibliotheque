package racine.test.livre;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TypeLivreController {

    private final TypeLivreService typeLivreService;

    public TypeLivreController(TypeLivreService typeLivreService) {
        this.typeLivreService = typeLivreService;
    }
}
