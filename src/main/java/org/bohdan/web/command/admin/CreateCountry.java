package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.entity.Country;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCountry extends Command {

    private static final Logger logger = Logger.getLogger(CreateCountry.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String nameEN = request.getParameter("createNameEN");
            String nameRU = request.getParameter("createNameRU");
            logger.info("Log: name country -->" + nameEN + ", " + nameRU);

            Country country = Country.create(nameEN, nameRU);

            boolean check = new CountryDao().create(country);
            logger.info("Log: check create country --> " + check);

            return Path.COMMAND_LIST_COUNTRY;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
