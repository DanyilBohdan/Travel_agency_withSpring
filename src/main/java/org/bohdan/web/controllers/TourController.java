package org.bohdan.web.controllers;

import org.apache.log4j.Logger;
import org.bohdan.web.services.CountryService;
import org.bohdan.web.services.TourService;
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

    private TourService tourService;
    private CountryService countryService;

    @Autowired
    public TourController(TourService tourService, CountryService countryService) {
        this.tourService = tourService;
        this.countryService = countryService;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView viewTours(HttpServletRequest request) {
        return tourService.viewTours(request);
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
