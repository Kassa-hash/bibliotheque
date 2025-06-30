package racine.test.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/")
    public String sayHello(Model model) {
       // model.addAttribute("message", "Hello, World!");
        return "hello"; // Nom du template (hello.html)
    }
}
