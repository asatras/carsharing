package com.carsharing.model.db.dao.impl;

import com.carsharing.model.db.ConnectionPool;
import com.carsharing.model.db.dao.DAOFactory;
import com.carsharing.model.db.dao.UserDAO;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactoryImpl extends DAOFactory {

    private static final Logger logger = Logger.getLogger(DAOFactoryImpl.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public UserDAO createUserDAO() throws SQLException {
        logger.debug("Creating UserDAO");
        return new UserDAOImpl(getConnection());
    }

    private Connection getConnection() {
        try {
            logger.debug("Trying to get connection from pool in DAOFactoryImpl");
            return connectionPool.getConnection();
        } catch (SQLException e) {
            logger.error("Cannot get connection from pool in DAOFactoryImpl", e);
            throw new RuntimeException(e);
        }
    }
}
