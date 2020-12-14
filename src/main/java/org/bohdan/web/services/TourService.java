package org.bohdan.web.services;

import org.bohdan.model.Country;
import org.bohdan.model.Tour;
import org.bohdan.model.TypeTour;
import org.bohdan.model.general.ListBean;
import org.bohdan.model.general.TourView;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface TourService {
    //    List<TourView> viewTours(HttpServletRequest request, String lang);
    List<TourView> viewTours(String lang);

    List<ListBean> getListCountry(String lang);

    List<ListBean> getListTypeTours(String lang);

    List<Country> getListCountry();

    List<TypeTour> getListTypeTours();

    Country getCountryById(Integer id);

    TypeTour getTypeTourById(Integer id);

    Tour getTourById(Integer id);

    TourView viewTour(String lang, Integer id);

    List<TourView> viewToursForAdmin(String lang);

    ModelAndView editTourView(HttpServletRequest request);

    ModelAndView editTour(HttpServletRequest request) throws IOException, ServletException;

    String deleteTour(int id);

    ModelAndView createTourView(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView createTour(HttpServletRequest request) throws IOException, ServletException;
}
