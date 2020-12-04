package org.bohdan.web.services;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.model.general.TourView;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * View one tour by id
 *
 * @author Bohdan Daniel
 */

public class ViewTourCommand {

    private static final Logger logger = Logger.getLogger(ViewTourCommand.class);

    public TourView execute(HttpServletRequest request, TourDao tourDao)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        String defLocale = (String) session.getAttribute("defLocale");
        logger.trace("LOG: defLocale = " + defLocale);

        int id = Integer.parseInt(request.getParameter("id"));
        logger.trace("LOG: id_tour = " + id);

        return tourDao.findByIdLocale(defLocale, id);
    }
}
