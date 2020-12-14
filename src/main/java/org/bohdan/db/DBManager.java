package org.bohdan.db;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

/**
 * DBManager. Works with Tests.
 *
 * @author Bohdan Daniel
 *
 */
@Component
public class DBManager extends ConnectionFactory {

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

    /**
     * @return Data Source
     */
    @Bean
    public static DataSource getDataSource() {
        return dataSource;
    }

    private DBManager() {
    }

    /**
     * singleton
     */
    private static DBManager dbManager;

    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
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