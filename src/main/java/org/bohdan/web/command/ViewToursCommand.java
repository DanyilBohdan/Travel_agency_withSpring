package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.bean.TourView;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;

public class ViewToursCommand extends Command {

    private final static Logger logger = Logger.getLogger(ViewToursCommand.class);

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

        request.setAttribute("typeTourOut", new TypeTourDao(dataSource).findByLocale(lang));
        request.setAttribute("countryOut", new CountryDao(dataSource).findByLocale(lang));

        List<TourView> tours = SearchTour.execute(request, response, 0);

        logger.trace("Found in DB: tours --> " + tours);

        tours.sort((o1, o2) -> Float.compare(o2.getDiscount(), o1.getDiscount()));

        request.setAttribute("tours", tours);

        request.setAttribute("commandPage", "viewTours");

        logger.debug("Command finished");

        return Path.PAGE_MAIN;
    }
}
