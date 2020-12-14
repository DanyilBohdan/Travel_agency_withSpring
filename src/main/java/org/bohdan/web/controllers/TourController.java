package org.bohdan.web.controllers;

import org.apache.log4j.Logger;
import org.bohdan.model.Country;
import org.bohdan.model.Tour;
import org.bohdan.model.TypeTour;
import org.bohdan.model.general.TourView;
import org.bohdan.web.Path;
import org.bohdan.web.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("tours")
public class TourController {

    private static final Logger logger = Logger.getLogger(TourController.class);

    private final TourService tourService;

    private final HttpSession session;

    @Autowired
    public TourController(TourService tourService, HttpSession session) {
        this.tourService = tourService;
        this.session = session;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView viewTours(HttpServletRequest request) {

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
        if (lang == null){
            lang = "EN";
        }

        ModelAndView modelAndView = new ModelAndView(Path.PAGE_MAIN);
        modelAndView.addObject("typeTourOut", tourService.getListTypeTours(lang));
        modelAndView.addObject("countryOut", tourService.getListCountry(lang));

        List<TourView> tours = tourService.viewTours(lang);
        if (tours.size() > 1) {
            tours.sort((o1, o2) -> Float.compare(o2.getDiscount(), o1.getDiscount()));
        }
        modelAndView.addObject("tours", tours);

        modelAndView.addObject("commandPage", "view");

        return modelAndView;
    }

    @RequestMapping(value = "view/tour", method = RequestMethod.GET)
    public ModelAndView viewTour(HttpServletRequest request) {

        String lang = (String) session.getAttribute("defLocale");
        logger.trace("LOG: defLocale = " + lang);

        int id = Integer.parseInt(request.getParameter("id"));
        logger.trace("LOG: id_tour = " + id);

        ModelAndView modelAndView = new ModelAndView(Path.PAGE_VIEW_TOUR);
        modelAndView.addObject("tour", tourService.viewTour(lang, id));
        return modelAndView;
    }

    @RequestMapping(value = "admin/view", method = RequestMethod.GET)
    public ModelAndView viewToursForAdmin(HttpServletRequest request) {
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
        if (lang == null){
            lang = "EN";
        }

        ModelAndView modelAndView = new ModelAndView(Path.LIST_TOURS_ADMIN);
        modelAndView.addObject("typeTourOut", tourService.getListTypeTours(lang));
        modelAndView.addObject("countryOut", tourService.getListCountry(lang));

        List<TourView> tours = tourService.viewToursForAdmin(lang);
        if (tours.size() > 1) {
            tours.sort((o1, o2) -> Float.compare(o2.getDiscount(), o1.getDiscount()));
        }
        modelAndView.addObject("tours", tours);

        modelAndView.addObject("commandPage", "view");

        return modelAndView;
    }

    @RequestMapping(value = "admin/tour/editView", method = RequestMethod.GET)
    public ModelAndView editTourView(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView(Path.EDIT_TOUR);

        int id = Integer.parseInt(request.getParameter("id"));
        Tour tourOld = tourService.getTourById(id);
        if (tourOld == null) {
            return new ModelAndView(Path.ERROR_PAGE);
        }

        modelAndView.addObject("tour", tourOld);

        TypeTour typeTour = tourService.getTypeTourById(tourOld.getTypeTourId());
        Country country = tourService.getCountryById(tourOld.getCountryId());
        if (typeTour == null || country == null) {
            return new ModelAndView(Path.ERROR_PAGE);
        }

        session.setAttribute("typeTourOut", tourService.getListTypeTours());
        modelAndView.addObject("typeDef", typeTour);
        logger.debug("Log: typeDef --> " + typeTour);

        session.setAttribute("countryOut", tourService.getListCountry());
        modelAndView.addObject("countryDef", country);
        logger.debug("Log: country --> " + country);

        return modelAndView;
//        return tourService.editTourView(request);
    }

    @RequestMapping(value = "admin/tour/edit", method = RequestMethod.POST)
    public ModelAndView editTour(HttpServletRequest request) throws IOException, ServletException {
        return tourService.editTour(request);
    }

    @RequestMapping(value = "admin/tour/delete", method = RequestMethod.POST)
    public ModelAndView deleteTour(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        return new ModelAndView(tourService.deleteTour(id));
    }

    @RequestMapping(value = "admin/tour/createView", method = RequestMethod.GET)
    public ModelAndView createTourView() {
        ModelAndView modelAndView = new ModelAndView(Path.CREATE_TOUR);

        List<TypeTour> typeTours = tourService.getListTypeTours();

        session.setAttribute("typeTourOut", typeTours);
        modelAndView.addObject("typeDef", typeTours.get(0));
        logger.debug("Log: typeDef --> " + typeTours.get(0));

        List<Country> countries = tourService.getListCountry();

        session.setAttribute("countryOut", countries);
        modelAndView.addObject("countryDef", countries.get(0));
        logger.debug("Log: country --> " + countries.get(0));

        return modelAndView;
    }

    @RequestMapping(value = "admin/tour/create", method = RequestMethod.POST)
    public ModelAndView createTour(HttpServletRequest request) throws IOException, ServletException {
        return tourService.createTour(request);
    }



}
