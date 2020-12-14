package org.bohdan.web.controllers;

import org.apache.log4j.Logger;
import org.bohdan.model.general.TourView;
import org.bohdan.web.Path;
import org.bohdan.web.services.TourService;
import org.bohdan.web.services.common.SearchTour;
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
    public ModelAndView viewTour(HttpServletRequest request) throws IOException, ServletException {
        return tourService.viewTour(request);
    }

    @RequestMapping(value = "admin/view", method = RequestMethod.GET)
    public ModelAndView viewToursForAdmin(HttpServletRequest request) {
        return tourService.viewToursForAdmin(request);
    }

    @RequestMapping(value = "admin/tour/editView", method = RequestMethod.GET)
    public ModelAndView editTourView(HttpServletRequest request) {
        return tourService.editTourView(request);
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
    public ModelAndView createTourView(HttpServletRequest request) throws IOException, ServletException {
        return tourService.createTourView(request);
    }

    @RequestMapping(value = "admin/tour/create", method = RequestMethod.POST)
    public ModelAndView createTour(HttpServletRequest request) throws IOException, ServletException {
        return tourService.createTour(request);
    }



}
