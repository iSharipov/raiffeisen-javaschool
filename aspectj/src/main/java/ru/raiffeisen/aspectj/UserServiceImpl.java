package ru.raiffeisen.aspectj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private final Map<Long, User> storage;

    public UserServiceImpl(Map<Long, User> storage) {
        this.storage = storage;
    }

    public UserDTO byId(long id) {
        Optional<User> user = Optional.empty();
        try {
            user = storage.entrySet().stream().filter(entity -> entity.getKey().equals(id)).map(Map.Entry::getValue).findAny();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while query for user");
        }
        if (!user.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        return new UserDTO.Mapper().mapToDTO(user.get());
    }

    @Counter
    public List<UserDTO> findAll() {
        List<User> users = null;
        try {
            users = new ArrayList<>(storage.values());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while query for users");
        }
        if (users == null) {
            throw new IllegalArgumentException("Users not found");
        }
        return new UserDTO.Mapper().mapToDTOs(users);
    }

    static {
        System.out.println("Static initializer");
    }
}