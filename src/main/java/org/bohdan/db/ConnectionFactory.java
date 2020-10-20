package org.bohdan.db;

import javax.sql.DataSource;

public interface ConnectionFactory {
    static DataSource getDataSource() {
        return null;
    }
}
