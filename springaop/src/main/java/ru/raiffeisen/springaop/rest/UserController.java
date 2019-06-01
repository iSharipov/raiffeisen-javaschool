package ru.raiffeisen.springaop.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.springaop.dto.UserDTO;
import ru.raiffeisen.springaop.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/person")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserById(@PathVariable long id) {
        return userService.byId(id);
    }
}