package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.ConnectionPool;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.entity.Country;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListCountry extends Command {

    private static final Logger logger = Logger.getLogger(ListCountry.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Country> countries = new CountryDao(dataSource).findAll();

        request.setAttribute("countries", countries);

        return Path.LIST_COUNTRY_ADMIN;
    }
}
