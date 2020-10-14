package com.carsharing.model.db.dao.impl;

import com.carsharing.model.db.ConnectionPool;
import com.carsharing.model.db.dao.DAOFactory;
import com.carsharing.model.db.dao.UserDAO;
import com.carsharing.model.db.dao.mapper.UserMapper;
import com.carsharing.model.db.entity.Order;
import com.carsharing.model.db.entity.Role;
import com.carsharing.model.db.entity.User;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final static String QUERY_ADD_USER = "INSERT INTO users (login, password, email, name, surname, passport, isActive, balance) VALUES (? ,? ,?, ?, ?, ?, ?, ?)";
    private final static String QUERY_ADD_USER_ROLE = "INSERT INTO users_has_roles (users_id, roles_id) VALUES (?, ?)";
    private final static String QUERY_FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?";
    private final static String QUERY_FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private final static String QUERY_FIND_ALL_USERS = "SELECT * FROM users";

    //TODO finish query
    private String QUERY_FIND_BY_ROLE = "SELECT id, email, password, active FROM carsharing_db.users  INNER JOIN carsharing_db.users_has_roles ON users.id=users_has_roles.users_id WHERE roles_id = ?";

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);
    private Connection connection;
    private UserMapper userMapper;

    public UserDAOImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.userMapper = new UserMapper();
    }

    private DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public User findByLoginAndPassword(String login, String password) {
        logger.debug("Method findByLoginAndPassword in UserDAOImpl starts");

        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            logger.debug("Got connection in findByLoginAndPassword method: " + connection);
            try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_USER_BY_LOGIN_AND_PASSWORD)) {
                int k = 1;

                logger.debug("Got login and password in findByLoginAndPassword: " + login + ", " + password);
                preparedStatement.setString(k++, "login");
                preparedStatement.setString(k++, "password");

                ResultSet resultSet = preparedStatement.executeQuery();
                logger.debug("resultSet is: " + resultSet.getString("login")
                                            + " " + resultSet.getString("password"));

                if (resultSet.next()) {
                    logger.debug("Founded user by login and password");
                    userMapper.mapRow(resultSet);
                } else {
                    logger.debug("No users were founded");
                }
            }
        } catch (SQLException e) {
            logger.error("Cannot find by login and password", e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User findByLogin(String login) {
        logger.debug("Got " + login + " parameter in findByLogin method");

        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            logger.debug("Calling findByLogin method");
            return findByLoginUsingConnection(login, connection);
        } catch (SQLException e) {
            logger.error("Exception in try section in findByLogin method ", e);
            throw new RuntimeException(e);
        }
    }

    public User findByLoginUsingConnection(String login, Connection connection) {
        logger.debug("Got " + login + " parameter in findByLoginUsingConnection method");

        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_BY_LOGIN)) {
            int k = 1;
            preparedStatement.setString(k++, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                logger.debug("Founded user by login " + login);
                return userMapper.mapRow(resultSet);
//                return extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Cannot find by login ", e);
            throw new RuntimeException(e);
        }
        logger.debug("Returning null from findByLoginUsingConnection method");
        return null;
    }

    @Override
    public void updateBalance(User user, BigDecimal value) {

    }

    @Override
    public void createOrder(User user, Order order) throws SQLException {

    }

    @Override
    public void add(User entity) throws SQLException {

        //TODO get connection and return to the pool
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_USER);
                 PreparedStatement preparedStatement1 = connection.prepareStatement(QUERY_ADD_USER_ROLE)) {
                int k = 1;
                preparedStatement.setString(k++, entity.getLogin());
                preparedStatement.setString(k++, entity.getPassword());
                preparedStatement.setString(k++, entity.getEmail());
                preparedStatement.setString(k++, entity.getName());
                preparedStatement.setString(k++, entity.getSurname());
                preparedStatement.setString(k++, entity.getPassport());
                preparedStatement.setBoolean(k++, entity.isActive());
                preparedStatement.setBigDecimal(k++, entity.getBalance());

                int j = preparedStatement.executeUpdate();
                logger.debug("Added first part of addUser transaction, rows added " + j);

                k = 1;
                logger.debug("k = " + k);
                logger.debug("entity.getLogin() paramert ==>" + entity.getLogin());
                preparedStatement1.setLong(k++, findByLoginUsingConnection(entity.getLogin(), connection).getId());
                preparedStatement1.setLong(k++, Arrays.asList(Role.values()).indexOf(entity.getRole()) + 1);
                preparedStatement1.executeUpdate();
                connection.commit();
                logger.debug("User has been added to DB");
            } catch (SQLException e) {
                logger.error("Cannot add user", e);
                ConnectionPool.getInstance().rollbackAndClose(connection);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
//        List<User> resultList = new CopyOnWriteArrayList<>();
//        try (UserDAO userDao = daoFactory.createUserDAO()) {
//            return userDao.findAll();
//        } catch (SQLException e) {
//            logger.error("Cannot find all users", e);
//            //TODO add pooling methods
//        }
        return null;
//        return resultList;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteEntity(User entity) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            //TODO log
            throw new RuntimeException(e);
        }
    }
}
