package org.bohdan.web.command.admin;

import org.bohdan.db.ConnectionPool;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

public class GetCreateTourPage extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setAttribute("typeTourOut", new TypeTourDao(dataSource).findAll());
        request.setAttribute("countryOut", new CountryDao(dataSource).findAll());
        return Path.CREATE_TOUR;
    }
}
