package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.entity.Country;
import org.bohdan.db.entity.TypeTour;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetCreateTourPage extends Command {

    private static final Logger logger = Logger.getLogger(GetCreateTourPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<TypeTour> typeTours = new TypeTourDao(connectionPool).findAll();
        List<Country> countries = new CountryDao(connectionPool).findAll();
        if (typeTours == null || countries == null) {
            return Path.ERROR_PAGE;
        }

        HttpSession session = request.getSession();

        session.setAttribute("typeTourOut", new TypeTourDao(connectionPool).findAll());
        request.setAttribute("typeDef", typeTours.get(0));
        logger.debug("Log: typeDef --> " + typeTours.get(0));

        session.setAttribute("countryOut", new CountryDao(connectionPool).findAll());
        request.setAttribute("countryDef", countries.get(0));
        logger.debug("Log: country --> " + countries.get(0));

        return Path.CREATE_TOUR;
    }
}
