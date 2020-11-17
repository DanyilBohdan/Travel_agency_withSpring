package org.bohdan.web.services;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.model.general.TourView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class ViewTourService{

    private static final Logger logger = Logger.getLogger(ViewTourService.class);

    private TourDao tourDao;

    @Autowired
    public ViewTourService(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    public ModelAndView execute(HttpServletRequest request, String nameView)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        String defLocale = (String) session.getAttribute("defLocale");
        logger.trace("LOG: defLocale = " + defLocale);

        int id = Integer.parseInt(request.getParameter("id"));
        logger.trace("LOG: id_tour = " + id);

        TourView tour = tourDao.findByIdLocale(defLocale, id);

        ModelAndView modelAndView = new ModelAndView(nameView);
        modelAndView.addObject("tour", tour);

        return modelAndView;
    }
}
