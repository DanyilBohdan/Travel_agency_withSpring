package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.bean.TourView;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListTours extends Command {

    private final static Logger logger = Logger.getLogger(ListTours.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

//        String localeToSet = request.getParameter("localeToSet");
//
//        if (localeToSet != null && !localeToSet.isEmpty()) {
//            HttpSession session = request.getSession();
//            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
//            session.setAttribute("defaultLocale", localeToSet);
//            session.setAttribute("localeDef", localeToSet);
//        }
//        logger.info("LOG: locale = " + localeToSet);

//        List<TourView> tours = null;
//        if (localeToSet != null) {
//            tours = new TourDao().findAllByLocale(localeToSet);
//        }

        List<TourView> tours = new TourDao().findAllByLocale("EN");

        logger.trace("Found in DB: tours --> " + tours);

        tours.sort((o1, o2) -> (int) o1.getId() - o2.getId());

        request.setAttribute("tours", tours);

        logger.debug("Command finished");

        return Path.LIST_TOURS_ADMIN;
    }
}
