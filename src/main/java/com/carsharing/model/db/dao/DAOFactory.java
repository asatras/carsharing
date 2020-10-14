package com.carsharing.model.db.dao;

import com.carsharing.model.db.dao.impl.DAOFactoryImpl;

import java.sql.SQLException;

public abstract class DAOFactory {

    private static volatile DAOFactory daoFactory;

    public abstract UserDAO createUserDAO() throws SQLException;

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DAOFactory.class) {
                if (daoFactory == null) {
                    DAOFactory temp = new DAOFactoryImpl();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
