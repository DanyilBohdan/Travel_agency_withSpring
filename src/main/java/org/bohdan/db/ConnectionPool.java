package org.bohdan.db;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);
    private static final String DATASOURCE_NAME = "jdbc/travel_agencyDB";
    private static final String JNDI = "java:/comp/env";

    private static DataSource dataSource;

    static {
        try {
            Context envContext = (Context) new InitialContext().lookup(JNDI);
            dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
//            logger.debug("Log: --> connection to DB - OK");
        } catch (NamingException e) {
//            logger.error("Cannot obtain a connection from the pool", e);
        }
    }

    private ConnectionPool() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
