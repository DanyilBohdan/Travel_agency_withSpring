package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.model.Country;
import org.bohdan.model.Tour;
import org.bohdan.model.TypeTour;
import org.bohdan.web.Path;
import org.bohdan.web.services.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * View edit tour page
 *
 * @author Bohdan Daniel
 */
public class GetEditTourPage extends Command {

    private static final Logger logger = Logger.getLogger(GetEditTourPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        Tour tourOld = new TourDao(connectionPool).findIDTour(id);
        if (tourOld == null) {
            return Path.ERROR_PAGE;
        }
        TypeTour typeTour = new TypeTourDao(connectionPool).findEntityById(tourOld.getTypeTourId());
        Country country = new CountryDao(connectionPool).findEntityById(tourOld.getCountryId());
        if (typeTour == null || country == null) {
            return Path.ERROR_PAGE;
        }

        HttpSession session = request.getSession();

        request.setAttribute("tour", tourOld);
        session.setAttribute("typeTourOut", new TypeTourDao(connectionPool).findAll());
        request.setAttribute("typeDef", typeTour);
        logger.debug("Log: typeDef --> " + typeTour);

        session.setAttribute("countryOut", new CountryDao(connectionPool).findAll());
        request.setAttribute("countryDef", country);
        logger.debug("Log: country --> " + country);

        return Path.EDIT_TOUR;
    }
}
