package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.bean.TourView;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;
import org.bohdan.web.command.SearchTour;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListTours extends Command {

    private final static Logger logger = Logger.getLogger(ListTours.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String lang = (String) request.getSession().getAttribute("defLocale");

        request.setAttribute("typeTourOut", new TypeTourDao().findByLocale(lang));
        request.setAttribute("countryOut", new CountryDao().findByLocale(lang));

        List<TourView> tours = SearchTour.execute(request, response);

        logger.trace("Found in DB: tours --> " + tours);

        tours.sort((o1, o2) -> (int) o1.getId() - o2.getId());

        request.setAttribute("tours", tours);

        logger.debug("Command finished");

        return Path.LIST_TOURS_ADMIN;
    }
}
