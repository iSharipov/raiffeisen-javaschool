package ru.raiffeisen.springaop.service;

import ru.raiffeisen.springaop.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO byId(long id);

    List<UserDTO> findAll();
}
