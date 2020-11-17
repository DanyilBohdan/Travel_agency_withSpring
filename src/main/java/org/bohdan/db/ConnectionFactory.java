package org.bohdan.db;

import javax.sql.DataSource;

public abstract class ConnectionFactory {
    public static DataSource dataSource;

    public static DataSource getDataSource() {
        return dataSource;
    }
}
