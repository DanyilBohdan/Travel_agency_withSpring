package org.bohdan.db;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBManager {

    private static final Logger logger = Logger.getLogger(DBManager.class);

    private DBManager() {
    }

    //   singleton

    private static DBManager dbManager;

    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public Connection getConnection() throws SQLException{
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            DataSource dataSource = (DataSource) envContext.lookup("jdbc/travel_agencydb");
            con = dataSource.getConnection();
        } catch (NamingException ex){
            ex.printStackTrace();
        }
        return con;
    }

    //public Connection getConnectionWithDriverManager() throws SQLException {
//    public Connection getConnection() throws SQLException {
//        Connection connection = null;
//        String url = getConnectionUrl();
//        if (url != null) {
//            connection = DriverManager.getConnection(url);
//        }
//        return connection;
//    }
//
//    private static String getConnectionUrl() {
//        Properties prop = new Properties();
//        try (InputStream inputStream = new FileInputStream("db_app.properties")) {
//            prop.load(inputStream);
//            return prop.getProperty("connection.url");
//        } catch (IOException ex) {
//            logger.error("Not connection URL", ex);
//        }
//        return null;
//    }

    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}