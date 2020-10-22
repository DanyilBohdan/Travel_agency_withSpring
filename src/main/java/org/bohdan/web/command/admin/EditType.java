package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.entity.TypeTour;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Edit one type by id
 *
 * @author Bohdan Daniel
 */
public class EditType extends Command {

    private static final Logger logger = Logger.getLogger(EditType.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        logger.debug("Log: id --> " + id);

        String nameEN = request.getParameter("nameEN");
        String nameRU = request.getParameter("nameRU");
        logger.debug("Log: name --> " + nameEN + ", " + nameRU);
        boolean val = Validation.validateLatin(nameEN);
        if (!val) {
            String errorMessage = "Type (EN) must be: only latin";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR_PAGE;
        }
        val = Validation.validateCyrillic(nameRU);
        if (!val) {
            String errorMessage = "Type (RU) must be: only cyrillic";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR_PAGE;
        }

        TypeTour typeTour = TypeTour.create(nameEN, nameRU);
        typeTour.setId(id);

        boolean check = new TypeTourDao(connectionPool).update(typeTour);
        logger.debug("Log: check update type --> " + check);

        return Path.COMMAND_LIST_TYPE;
    }
}
