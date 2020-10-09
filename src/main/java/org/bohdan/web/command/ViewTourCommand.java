package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.bean.TourView;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

public class ViewTourCommand extends Command{

    private static final Logger logger = Logger.getLogger(ViewTourCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        String defLocale = (String) Config.get(session, "javax.servlet.jsp.jstl.fmt.locale");
        logger.trace("LOG: defLocale = " + defLocale);

        int id = Integer.parseInt(request.getParameter("id"));
        logger.trace("LOG: id = " + id);

        TourView tour = new TourDao().findByIdLocale("EN", id);

        request.setAttribute("tour", tour);

        return Path.VIEW_TOUR_ID;
    }
}
