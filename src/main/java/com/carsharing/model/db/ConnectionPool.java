package com.carsharing.model.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final Log logger = LogFactory.getLog(ConnectionPool.class);
    private static ConnectionPool instance;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/carsharing_db");
            connection = dataSource.getConnection();
            logger.debug("Connection from connection pool ----> OK!");
        } catch (NamingException e) {
            logger.error("Cannot get connection from connection puul", e);
        }
        return connection;
    }

    /**
     * Commits and close the given connection.
     *
     * @param con Connection to be committed and closed.
     */
    public void commitAndClose(Connection con) throws SQLException {
        try {
            if (con != null) {
                con.commit();
                logger.debug("Connection commited");
                con.close();
                logger.debug("Connection closed");
            } else {
                logger.debug("Connection equals null");
            }
        } catch (SQLException e) {
            logger.error("Cannot commit and close", e);
            throw new SQLException();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection con) throws SQLException {
        try {
            if (con != null) {
                con.rollback();
                logger.debug("Connection rollback");
                con.close();
                logger.debug("Connection closed");
            } else {
                logger.debug("Connection equals null");
            }
        } catch (SQLException e) {
            logger.error("Cannot rollback and close", e);
            throw new SQLException();
        }
    }
}
