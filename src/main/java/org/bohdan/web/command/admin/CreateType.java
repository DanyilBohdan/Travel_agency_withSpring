package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.entity.TypeTour;
import org.bohdan.web.Path;
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

            TypeTour typeTour = TypeTour.create(nameEN, nameRU);

            boolean check = new TypeTourDao().create(typeTour);
            logger.info("Log: check create type --> " + check);

            return Path.COMMAND_LIST_TYPE;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
