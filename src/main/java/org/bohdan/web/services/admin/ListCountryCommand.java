package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.model.Country;
import org.bohdan.web.Path;
import org.bohdan.web.services.Command;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;

/**
 * View list countries page
 *
 * @author Bohdan Daniel
 */
public class ListCountryCommand {

    private static final Logger logger = Logger.getLogger(ListCountryCommand.class);

    public ModelAndView view(HttpServletRequest request, ModelAndView modelAndView, CountryDao countryDao) throws IOException, ServletException {

        HttpSession session = request.getSession();
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

        List<Country> countries = countryDao.findAll();

        modelAndView.addObject("countries", countries);

        modelAndView.addObject("commandPage", "listCountry");

        return modelAndView;
    }
}
