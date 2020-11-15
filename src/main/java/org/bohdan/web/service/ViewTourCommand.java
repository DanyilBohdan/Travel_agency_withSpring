package org.bohdan.web.service;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.model.general.TourView;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * View one tour by id
 *
 * @author Bohdan Daniel
 */
public class ViewTourCommand extends Command{

    private static final Logger logger = Logger.getLogger(ViewTourCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        String defLocale = (String) session.getAttribute("defLocale");
        logger.trace("LOG: defLocale = " + defLocale);

        int id = Integer.parseInt(request.getParameter("id"));
        logger.trace("LOG: id_tour = " + id);

        TourView tour = new TourDao(connectionPool).findByIdLocale(defLocale, id);

        request.setAttribute("tour", tour);

        return Path.PAGE_VIEW_TOUR;
    }
}
