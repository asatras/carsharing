package com.carsharing.model.db.dao;

import com.carsharing.model.db.entity.Order;
import com.carsharing.model.db.entity.User;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface UserDAO extends GenericDAO<User> {

    User findByLoginAndPassword(String username, String password);

    User findByLogin(String login);

    void updateBalance(User user, BigDecimal value);

    void createOrder(User user, Order order) throws SQLException;
}
