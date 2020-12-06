package org.bohdan.web.services.impl;

import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.web.Path;
import org.bohdan.web.services.TourService;
import org.bohdan.web.services.common.ViewTourCommand;
import org.bohdan.web.services.common.ViewToursCommand;
import org.bohdan.web.services.admin.DeleteTourCommand;
import org.bohdan.web.services.admin.TourCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class TourServiceImpl implements TourService {

    private TypeTourDao typeTourDao;
    private CountryDao countryDao;
    private TourDao tourDao;

    @Autowired
    public TourServiceImpl(TypeTourDao typeTourDao, CountryDao countryDao, TourDao tourDao) {
        this.typeTourDao = typeTourDao;
        this.countryDao = countryDao;
        this.tourDao = tourDao;
    }

    @Override
    public ModelAndView viewTours(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Path.PAGE_MAIN);
        return new ViewToursCommand().execute(request, modelAndView, typeTourDao, countryDao, tourDao, 0);
    }

    @Override
    public ModelAndView viewTour(HttpServletRequest request) throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.PAGE_VIEW_TOUR);
        modelAndView.addObject("tour", new ViewTourCommand().execute(request, tourDao));
        return modelAndView;
    }

    @Override
    public ModelAndView viewToursForAdmin(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Path.LIST_TOURS_ADMIN);
        return new ViewToursCommand().execute(request, modelAndView, typeTourDao, countryDao, tourDao, 1);
    }

    @Override
    public ModelAndView editTourView(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Path.EDIT_TOUR);
        return new TourCommand().editTourView(request, modelAndView, tourDao, typeTourDao, countryDao);
    }

    @Override
    public ModelAndView editTour(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Path.EDIT_TOUR);
        return new TourCommand().editTour(request, modelAndView, tourDao, typeTourDao, countryDao);
    }

    @Override
    public String deleteTour(int id) {
        return new DeleteTourCommand().execute(id, tourDao);
    }

    @Override
    public ModelAndView createTourView(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Path.CREATE_TOUR);
        return new TourCommand().createTourView(request, modelAndView, typeTourDao, countryDao);
    }

    @Override
    public ModelAndView createTour(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Path.COMMAND_TOURS_ADMIN);
        return new TourCommand().createTour(request, modelAndView, tourDao, typeTourDao, countryDao);
    }

}
