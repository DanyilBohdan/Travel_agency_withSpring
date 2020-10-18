package org.bohdan.web.command;

import org.bohdan.db.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {


    protected DataSource dataSource = ConnectionPool.getDataSource();

    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
