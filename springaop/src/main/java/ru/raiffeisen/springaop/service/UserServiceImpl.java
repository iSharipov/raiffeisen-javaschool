package ru.raiffeisen.springaop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.raiffeisen.springaop.dto.UserDTO;
import ru.raiffeisen.springaop.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());

    private final JdbcTemplate jdbcTemplate;

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public UserDTO byId(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(
                    sql, new Object[]{id}, (resultSet, i) -> {
                        final User user1 = new User();
                        user1.setId(resultSet.getLong("id"));
                        user1.setName(resultSet.getString("name"));
                        user1.setEmail(resultSet.getString("email"));
                        return user1;
                    });
        } catch (Exception e) {
            logger.error("Error while query for user");
        }
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return new UserDTO.Mapper().mapToDTO(user);
    }

    @Audit
    @Counter
    public List<UserDTO> findAll() {
        String sql = "SELECT * FROM users";
        List<User> users = null;
        try {
            users = jdbcTemplate.query(
                    sql, (resultSet, i) -> {
                        final User user1 = new User();
                        user1.setId(resultSet.getLong("id"));
                        user1.setName(resultSet.getString("name"));
                        user1.setEmail(resultSet.getString("email"));
                        return user1;
                    });
        } catch (Exception e) {
            logger.error("Error while query for user");
        }
        if (users == null) {
            throw new IllegalArgumentException("User not found");
        }
        return new UserDTO.Mapper().mapToDTOs(users);
    }
}