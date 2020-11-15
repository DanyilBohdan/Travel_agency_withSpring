package org.bohdan.web.service.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.model.Country;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;
import org.bohdan.web.service.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Edit one country
 *
 * @author Bohdan Daniel
 */
public class EditCountry extends Command {

    private static final Logger logger = Logger.getLogger(EditCountry.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        logger.debug("Log: id --> " + id);

        String nameEN = request.getParameter("nameEN");
        String nameRU = request.getParameter("nameRU");
        logger.debug("Log: name --> " + nameEN + ", " + nameRU);
        boolean val = Validation.validateLatin(nameEN);
        if (!val) {
            String errorMessage = "Country (EN) must be: only latin";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR_PAGE;
        }
        val = Validation.validateCyrillic(nameRU);
        if (!val) {
            String errorMessage = "Country (RU) must be: only cyrillic";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR_PAGE;
        }

        Country country = Country.create(nameEN, nameRU);
        country.setId(id);

        boolean check = new CountryDao(connectionPool).update(country);
        logger.debug("Log: check update country --> " + check);

        return Path.COMMAND_LIST_COUNTRY;
    }
}
