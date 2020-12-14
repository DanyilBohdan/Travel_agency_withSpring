package org.bohdan.web.services.impl;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.model.Country;
import org.bohdan.model.Tour;
import org.bohdan.model.TypeTour;
import org.bohdan.model.general.ListBean;
import org.bohdan.model.general.TourView;
import org.bohdan.web.Path;
import org.bohdan.web.services.TourService;
import org.bohdan.web.services.admin.DeleteTourCommand;
import org.bohdan.web.services.admin.TourCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {

    private static final Logger logger = Logger.getLogger(TourServiceImpl.class);

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
    public List<TourView> viewTours(String lang) {
//        List<TourView> tours = SearchTour.execute(request, tourDao, 0, lang);
        tourDao.setFilter(0);
        return tourDao.findAllByLocale(lang, 0, 6);
    }

    @Override
    public List<ListBean> getListCountry(String lang) {
        return countryDao.findByLocale(lang);
    }

    @Override
    public List<ListBean> getListTypeTours(String lang) {
        return typeTourDao.findByLocale(lang);
    }

    @Override
    public List<Country> getListCountry() {
        return countryDao.findAll();
    }

    @Override
    public List<TypeTour> getListTypeTours() {
        return typeTourDao.findAll();
    }

    @Override
    public TourView viewTour(String lang, Integer id) {
        return tourDao.findByIdLocale(lang, id);
    }

    @Override
    public List<TourView> viewToursForAdmin(String lang) {
        tourDao.setFilter(1);
        return tourDao.findAllByLocale(lang, 1, 20);
        //return new ViewToursCommand().execute(request, modelAndView, typeTourDao, countryDao, tourDao, 1);
    }

    @Override
    public ModelAndView editTourView(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Path.EDIT_TOUR);
        return new TourCommand().editTourView(request, modelAndView, tourDao, typeTourDao, countryDao);
    }

    @Override
    public Country getCountryById(Integer id) {
        return countryDao.findEntityById(id);
    }

    @Override
    public TypeTour getTypeTourById(Integer id) {
        return typeTourDao.findEntityById(id);
    }

    @Override
    public Tour getTourById(Integer id) {
        return tourDao.findIDTour(id);
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
