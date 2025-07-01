package racine.test.admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("message", "Hello, World!");
        return "admin"; // Nom du template (hello.html)
    }

}
