package com.carsharing.model.db;//package com.carsharing.model.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {

    private static final Log logger = LogFactory.getLog(DBManager.class);

    private static DBManager instance;
    private DataSource dataSource;

    public static DBManager getInstance() throws Exception {
        logger.debug("Getting DBManager instance");
        if (instance == null)
            synchronized (DBManager.class) {
                if (instance == null) {
                    instance = new DBManager();
                }
            }
        return instance;
    }

    private DBManager() throws Exception {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            logger.trace("trying to assign envContext.lookup to dataSource");
            dataSource = (DataSource) envContext.lookup("jdbc/carsharing_db");
        } catch (NamingException ex) {
            logger.error("DBManager exception, cannot obtain the data source", ex);
            throw new Exception(ex);
        }
    }
}
