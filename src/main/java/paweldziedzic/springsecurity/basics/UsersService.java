package paweldziedzic.springsecurity.basics;

import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsersService {

    private final HashMap<String, Integer> users = new HashMap<>();

    public HashMap<String, Integer> checkUsers(HashMap<String, Integer> existedUsers, Principal principal) {
        if (existedUsers.isEmpty() || !(existedUsers.containsKey(principal.getName())))
            existedUsers.put(principal.getName(), 1);
        else {
            existedUsers.entrySet().iterator();
            for (Map.Entry<String, Integer> el : existedUsers.entrySet()) {
                if (el.getKey().equals(principal.getName()))
                    el.setValue(el.getValue() + 1);
            }
        }
        return existedUsers;
    }

    public HashMap<String, Integer> getUsers() {
        return users;
    }
}