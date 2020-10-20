package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.ConnectionPool;
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
import javax.sql.DataSource;
import java.io.IOException;

public class GetCreateTourPage extends Command {

    private static final Logger logger = Logger.getLogger(GetCreateTourPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//        TypeTour typeTour = new TypeTourDao(dataSource).findEntityById(1);
//        Country country = new CountryDao(dataSource).findEntityById(1);
//        if (typeTour == null || country == null) {
//            return Path.ERROR_PAGE;
//        }

        HttpSession session = request.getSession();

        session.setAttribute("typeTourOut", new TypeTourDao(dataSource).findAll());
//        request.setAttribute("typeDef", typeTour);
//        logger.debug("Log: typeDef --> " + typeTour);

        session.setAttribute("countryOut", new CountryDao(dataSource).findAll());
//        request.setAttribute("countryDef", country);
//        logger.debug("Log: country --> " + country);

        return Path.CREATE_TOUR;
    }
}
