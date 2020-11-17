package org.bohdan.web.controllers;

import org.apache.log4j.Logger;
import org.bohdan.web.Path;
import org.bohdan.web.services.ViewTourService;
import org.bohdan.web.services.ViewToursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("tours")
public class TourController {

    private static final Logger logger = Logger.getLogger(TourController.class);

    private ViewToursService viewToursService;
    private ViewTourService viewTourService;

    @Autowired
    public TourController(ViewToursService viewToursService, ViewTourService viewTourService) {
        this.viewToursService = viewToursService;
        this.viewTourService = viewTourService;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView viewTours(HttpServletRequest request) {
        return viewToursService.execute(request, Path.PAGE_MAIN);
    }

    @RequestMapping(value = "view/tour", method = RequestMethod.GET)
    public ModelAndView viewTour(HttpServletRequest request) throws IOException, ServletException {
        return viewTourService.execute(request, Path.PAGE_VIEW_TOUR);
    }


}
