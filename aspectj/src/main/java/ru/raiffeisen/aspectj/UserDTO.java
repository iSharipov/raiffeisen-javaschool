package ru.raiffeisen.aspectj;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class Mapper {

        public UserDTO mapToDTO(User user) {
            final UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            return userDTO;
        }

        public List<UserDTO> mapToDTOs(List<User> users) {
            List<UserDTO> userDTOs = new ArrayList<>();
            users.forEach(user -> {
                userDTOs.add(mapToDTO(user));
            });
            return userDTOs;
        }
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
