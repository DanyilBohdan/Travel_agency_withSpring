package org.bohdan.web.service;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.model.general.TourView;
import org.bohdan.web.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;

/**
 * View all tours
 *
 * @author Bohdan Daniel
 */

@Service
public class ViewToursService {

    private final static Logger logger = Logger.getLogger(ViewToursService.class);

    private TypeTourDao typeTourDao;
    private CountryDao countryDao;
    private TourDao tourDao;

    @Autowired
    public ViewToursService(TypeTourDao typeTourDao, CountryDao countryDao, TourDao tourDao) {
        this.typeTourDao = typeTourDao;
        this.countryDao = countryDao;
        this.tourDao = tourDao;
    }

    public ModelAndView execute(HttpServletRequest request, String nameView) {

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


        List<TourView> tours = SearchTour.execute(request, 0);
        logger.trace("Found in DB: tours --> " + tours);

        logger.trace("Found in DB: tours --> " + tours);

        tours.sort((o1, o2) -> Float.compare(o2.getDiscount(), o1.getDiscount()));

        ModelAndView modelAndView = new ModelAndView(nameView);

        modelAndView.addObject("tours", tours);

        modelAndView.addObject("commandPage", "viewTours");

        logger.debug("Command finished");

        return modelAndView;
    }
}
