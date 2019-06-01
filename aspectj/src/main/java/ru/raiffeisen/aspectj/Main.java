package ru.raiffeisen.aspectj;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Long, User> users = new HashMap<Long, User>() {{
            put(1L, new User("mkyong", "mkyong@gmail.com"));
            put(2L, new User("alex", "alex@yahoo.com"));
            put(3L, new User("joel", "joel@gmail.com"));
        }};
        UserService userService = new UserServiceImpl(users);
        UserDTO userDTO = userService.byId(1);
        System.out.println("From main byId: " + userDTO);
        List<UserDTO> all = userService.findAll();
        System.out.println("From main findAll: " + all);
    }
}
