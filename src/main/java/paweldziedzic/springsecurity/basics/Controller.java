package paweldziedzic.springsecurity.basics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class Controller {


    private final UsersService usersService;

    @Autowired
    public Controller(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/helloSomebody")
    public String helloStrange(Principal principal) {
        if (principal == null)
            return "Hello, strange";
        return "Hello " + principal.getName();
    }

    @GetMapping("/bye")
    public String papa() {
        return "Bye";
    }

    @GetMapping("/helloUser")
    public String forUser(Principal principal) {
        int counterAuth = usersService.calculateNumberOfAuth(principal);
        int counterVisitStartPage = usersService.calculateNumberOfVisitsStartPage(principal);
        return "Hello user " + principal.getName() + ", you have been authenticated "+ counterAuth + " time(s)"+
                " and you visited Start page " + counterVisitStartPage + " time(s)";
    }

    @GetMapping("/helloAdmin")
    public String forAdmin(Principal principal) {
        return "Cześć, admin " + principal.getName();
    }
}