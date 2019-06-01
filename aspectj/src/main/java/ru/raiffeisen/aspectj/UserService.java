package ru.raiffeisen.aspectj;

import java.util.List;

public interface UserService {

    UserDTO byId(long id);

    List<UserDTO> findAll();
}
