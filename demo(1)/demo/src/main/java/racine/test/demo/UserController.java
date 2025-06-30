package racine.test.demo;

import racine.test.demo.User;
import racine.test.demo.UserService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestParam String name,
                           @RequestParam String email) {
        return userService.createUser(name, email);
    }

    @GetMapping("/by-email")
    public User getUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }
}
