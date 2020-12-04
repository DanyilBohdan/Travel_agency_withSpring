package org.bohdan.web.services.impl;

import org.bohdan.db.DAO.CountryDao;
import org.bohdan.web.Path;
import org.bohdan.web.services.CountryService;
import org.bohdan.web.services.admin.ListCountryCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public ModelAndView viewCountries(HttpServletRequest request) throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.LIST_COUNTRY_ADMIN);
        return new ListCountryCommand().view(request, modelAndView, countryDao);
    }

    @Override
    public ModelAndView createCountry(HttpServletRequest request) {
        return null;
    }

    @Override
    public ModelAndView deleteCountry(HttpServletRequest request) {
        return null;
    }

    @Override
    public ModelAndView editCountry(HttpServletRequest request) {
        return null;
    }
}
