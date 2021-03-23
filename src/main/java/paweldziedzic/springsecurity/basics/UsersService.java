package paweldziedzic.springsecurity.basics;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsersService implements ApplicationListener<AuthenticationSuccessEvent> {

    private final HashMap<String, Integer> users;

    private final HashMap<String, Integer> authenticationCounterUsers;

    public UsersService() {
        this.users = new HashMap<>();
        this.authenticationCounterUsers = new HashMap<>();
    }

    public Integer calculateNumberOfVisitsStartPage(Principal principal) {
        if (users.isEmpty() || !(users.containsKey(principal.getName()))) {
            users.put(principal.getName(), 1);
            return users.get(principal.getName());
        } else {
            users.entrySet().iterator();
            for (Map.Entry<String, Integer> el : users.entrySet()) {
                if (el.getKey().equals(principal.getName())) {
                    el.setValue(el.getValue() + 1);
                    return users.get(el.getKey());
                }
            }
        }
        return 0;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        String username = e.getAuthentication().getName();
        if(authenticationCounterUsers.containsKey(username)) {
            authenticationCounterUsers.put(username, authenticationCounterUsers.get(username) + 1);
        }
        else {
            authenticationCounterUsers.put(username, 1);
        }
    }

    public Integer calculateNumberOfAuth(Principal principal) {
        authenticationCounterUsers.entrySet().iterator();
        for (Map.Entry<String, Integer> el : authenticationCounterUsers.entrySet()) {
            if (el.getKey().equals(principal.getName()))
                return authenticationCounterUsers.get(el.getKey());
        }
        return 0;
    }
}