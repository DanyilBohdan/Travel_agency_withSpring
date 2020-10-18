package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.ConnectionPool;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.entity.TypeTour;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateType extends Command {

    private static final Logger logger = Logger.getLogger(CreateType.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String nameEN = request.getParameter("createNameEN");
            String nameRU = request.getParameter("createNameRU");
            logger.info("Log: name type -->" + nameEN + ", " + nameRU);
            boolean val = Validation.validateLatin(nameEN);
            if (!val) {
                String errorMessage = "Type (EN) must be: only latin";
                request.setAttribute("errorMessage", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
                return Path.ERROR_PAGE;
            }
            val = Validation.validateCyrillic(nameEN);
            if (!val) {
                String errorMessage = "Type (RU) must be: only cyrillic";
                request.setAttribute("errorMessage", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
                return Path.ERROR_PAGE;
            }

            TypeTour typeTour = TypeTour.create(nameEN, nameRU);

            boolean check = new TypeTourDao(dataSource).create(typeTour);
            logger.info("Log: check create type --> " + check);

            return Path.COMMAND_LIST_TYPE;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
