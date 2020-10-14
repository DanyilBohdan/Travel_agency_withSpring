package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.entity.Country;
import org.bohdan.db.entity.Tour;
import org.bohdan.db.entity.TypeTour;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetEditTourPage extends Command {

    private static final Logger logger = Logger.getLogger(GetEditTourPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        Tour tourOld = new TourDao().findIDTour(id);
        if (tourOld == null) {
            return Path.ERROR_PAGE;
        }
        TypeTour typeTour = new TypeTourDao().findEntityById(tourOld.getType_tour_id());
        Country country = new CountryDao().findEntityById(tourOld.getCountry_id());
        if (typeTour == null || country == null) {
            return Path.ERROR_PAGE;
        }

        request.setAttribute("tour", tourOld);
        request.setAttribute("typeTourOut", new TypeTourDao().findAll());
        request.setAttribute("typeDef", typeTour);
        logger.debug("Log: typeDef --> " + typeTour);

        request.setAttribute("countryOut", new CountryDao().findAll());
        request.setAttribute("countryDef", country);
        logger.debug("Log: country --> " + country);

        request.getSession().setAttribute("idTour", id);

        return Path.EDIT_TOUR;
    }
}
