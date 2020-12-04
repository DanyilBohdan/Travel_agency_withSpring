package org.bohdan.web.services;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface CountryService {

    ModelAndView viewCountries(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView createCountry(HttpServletRequest request);

    ModelAndView deleteCountry(HttpServletRequest request);

    ModelAndView editCountry(HttpServletRequest request);
}
