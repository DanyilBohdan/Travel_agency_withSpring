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

public class EditCountry extends Command {

    private static final Logger logger = Logger.getLogger(EditCountry.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        logger.debug("Log: id --> " + id);

        String nameEN = request.getParameter("nameEN");
        String nameRU = request.getParameter("nameRU");
        logger.debug("Log: name --> " + nameEN + ", " + nameRU);

        Country country = Country.create(nameEN, nameRU);
        country.setId(id);

        boolean check = new CountryDao().update(country);
        logger.debug("Log: check update country --> " + check);

        return Path.COMMAND_LIST_COUNTRY;
    }
}
