package org.bohdan.web.service.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.model.general.TourView;
import org.bohdan.web.Path;
import org.bohdan.web.service.Command;
import org.bohdan.web.service.SearchTour;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;

/**
 * View list tours page
 *
 * @author Bohdan Daniel
 */
public class ListTours extends Command {

    private final static Logger logger = Logger.getLogger(ListTours.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");
        logger.info("LOG: localeParam = " + lang);

        if (lang != null && !lang.isEmpty()) {
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
            session.setAttribute("defLocale", lang);
        } else {
            lang = (String) session.getAttribute("defLocale");
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
        }
        logger.info("LOG: localeFinal = " + lang);

        request.setAttribute("typeTourOut", new TypeTourDao(connectionPool).findByLocale(lang));
        request.setAttribute("countryOut", new CountryDao(connectionPool).findByLocale(lang));

        List<TourView> tours = SearchTour.execute(request, response, 1);

        logger.trace("Found in DB: tours --> " + tours);

        tours.sort((o1, o2) -> (int) o1.getId() - o2.getId());

        request.setAttribute("tours", tours);

        request.setAttribute("commandPage", "listTours");

        logger.debug("Command finished");

        return Path.LIST_TOURS_ADMIN;
    }
}
