package paweldziedzic.springsecurity.basics;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;


@RestController
public class Controller {

    private final UsersService usersService;

    public Controller(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/helloSomebody")
    public String helloStrange(Principal principal) {
        if (principal == null)
            return "Cześć, nieznajomy";
        return "Cześć " + principal.getName();
    }

    @GetMapping("/papa")
    public String papa() {
        return "Papa";
    }

    @GetMapping("/helloUser")
    public String forUser(Principal principal) {
        HashMap<String, Integer> users = usersService.getUsers();
        users = usersService.checkUsers(users, principal);
        return "Cześć, user " + principal.getName() + ", logowałeś się już " + users.get(principal.getName()) + " raz(y)";
    }

    @GetMapping("/helloAdmin")
    public String forAdmin(Principal principal) {
        return "Cześć, admin " + principal.getName();
    }

}