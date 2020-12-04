package org.bohdan.web.services;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.model.general.TourView;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.util.List;

/**
 * View all tours
 *
 * @author Bohdan Daniel
 */

public class ViewToursCommand {

    private final static Logger logger = Logger.getLogger(ViewToursCommand.class);

    public ModelAndView execute(HttpServletRequest request, ModelAndView modelAndView, TypeTourDao typeTourDao,
                                CountryDao countryDao, TourDao tourDao, int check) {

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

        modelAndView.addObject("typeTourOut", typeTourDao.findByLocale(lang));
        modelAndView.addObject("countryOut", countryDao.findByLocale(lang));

        List<TourView> tours = SearchTour.execute(request, tourDao, check);
        logger.trace("Found in DB: tours --> " + tours + tourDao);

        tours.sort((o1, o2) -> Float.compare(o2.getDiscount(), o1.getDiscount()));

        modelAndView.addObject("tours", tours);

        modelAndView.addObject("commandPage", "view");

        logger.debug("Command finished");

        return modelAndView;
    }
}
