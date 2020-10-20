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
import java.util.List;

public class ListType extends Command {

    private static final Logger logger = Logger.getLogger(ListType.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<TypeTour> typeTours = new TypeTourDao(connectionPool).findAll();

        request.setAttribute("types", typeTours);

        return Path.LIST_TYPE_ADMIN;
    }
}
