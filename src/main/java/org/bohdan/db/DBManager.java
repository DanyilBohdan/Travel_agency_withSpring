package org.bohdan.db;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import java.io.*;
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

    public Connection getConnection() {
        Connection con = null;
        try {
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setURL(ContextDB.URL);
            ds.setUser(ContextDB.USERNAME);
            ds.setPassword(ContextDB.PASSWORD);
            ds.setServerTimezone(ContextDB.SERVER_TIMEZONE);
            con = ds.getConnection();
        } catch (SQLException ex) {
            logger.error("Cannot obtain a connection from the pool", ex);
        }
        return con;
    }

    public static void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}