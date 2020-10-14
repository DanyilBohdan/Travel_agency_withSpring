package org.bohdan.web.command.admin;

import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetCreateTourPage extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("typeTourOut", new TypeTourDao().findAll());
        request.setAttribute("countryOut", new CountryDao().findAll());
        return Path.CREATE_TOUR;
    }
}
