package com.carsharing.model.service;

import com.carsharing.model.db.dao.DAOFactory;
import com.carsharing.model.db.dao.UserDAO;
import com.carsharing.model.db.entity.Role;
import com.carsharing.model.db.entity.User;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);
    private DAOFactory daoFactory = DAOFactory.getInstance();

    public void addUser(String login, String password, String email, String name, String surname, String passport) {
        User newUser = User.builder()
                .login(login)
                .password(password)
                .email(email)
                .name(name)
                .surname(surname)
                .passport(passport)
                .role(Role.USER)
                .balance(BigDecimal.valueOf(0))
                .isActive(true)
                .build();

        try (UserDAO userDAO = daoFactory.createUserDAO()) {
            userDAO.add(newUser);
        } catch (SQLException e) {
            logger.error("Cannot add user", e);
        }
    }

    public Optional<User> findUser(String login, String password) {
        Optional<User> user = Optional.empty();
        try (UserDAO userDAO = daoFactory.createUserDAO()) {
            user = Optional.ofNullable(userDAO
                    .findByLoginAndPassword(login, password));

            logger.debug("Founded user: " + user.toString());

            return user;
        } catch (SQLException e) {
            logger.error("Cannot find user by login and password", e);
        }
        return user;
    }

    public Optional<User> findByLogin(String login) {
        Optional<User> user = Optional.empty();
        try (UserDAO userDAO = daoFactory.createUserDAO()) {
            user = Optional.ofNullable(
                    userDAO.findByLogin(login));
            return user;
        } catch (SQLException e) {
            logger.error("Cannot find user by login", e);
        }
        return user;
    }
}
