package org.bohdan.db;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

public class DBManager implements ConnectionFactory{

    private static final Logger logger = Logger.getLogger(DBManager.class);

    private static DataSource dataSource;

    static {
        try {
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setURL(ContextDB.URL);
            ds.setUser(ContextDB.USERNAME);
            ds.setPassword(ContextDB.PASSWORD);
            ds.setServerTimezone(ContextDB.SERVER_TIMEZONE);
            dataSource = ds;
        } catch (SQLException ex) {
            logger.error("Cannot obtain a connection from the pool", ex);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public DBManager() {
    }

//    public static Connection getConnection() throws SQLException {
//        return dataSource.getConnection();
//    }

//    public Connection getConnection() throws SQLException {
//        return ConnectionPool.getConnection();
//    }

//    @Override
//    public Connection getConnection() {
//        java.sql.Connection con = null;
//        try {
//            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
//            ds.setURL(ContextDB.URL);
//            ds.setUser(ContextDB.USERNAME);
//            ds.setPassword(ContextDB.PASSWORD);
//            ds.setServerTimezone(ContextDB.SERVER_TIMEZONE);
//            DataSource dataSource = ds;
//            con = ds.getConnection();
//        } catch (SQLException ex) {
//            logger.error("Cannot obtain a connection from the pool", ex);
//        }
//        return con;
//    }

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