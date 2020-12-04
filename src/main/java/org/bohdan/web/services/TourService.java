package org.bohdan.web.services;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface TourService {
    ModelAndView viewTours(HttpServletRequest request);

    ModelAndView viewTour(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView viewToursForAdmin(HttpServletRequest request);

    ModelAndView editTourView(HttpServletRequest request);

    ModelAndView editTour(HttpServletRequest request) throws IOException, ServletException;

    String deleteTour(int id);

    ModelAndView createTourView(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView createTour(HttpServletRequest request) throws IOException, ServletException;
}
