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

public class EditType extends Command {

    private static final Logger logger = Logger.getLogger(EditType.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        logger.debug("Log: id --> " + id);

        String nameEN = request.getParameter("nameEN");
        String nameRU = request.getParameter("nameRU");
        logger.debug("Log: name --> " + nameEN + ", " + nameRU);

        TypeTour typeTour = TypeTour.create(nameEN, nameRU);
        typeTour.setId(id);

        boolean check = new TypeTourDao().update(typeTour);
        logger.debug("Log: check update type --> " + check);

        return Path.COMMAND_LIST_TYPE;
    }
}
