package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.bean.TourView;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewTours extends Command {

    private final static Logger logger = Logger.getLogger(ViewTours.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        logger.info("LOG: locale = " + request.getParameter("locale"));

        List<TourView> tours = new TourDao().findAllByLocale("RU");

        logger.trace("Found in DB: tours --> " + tours.toString());

//        Collections.sort(tours, new Comparator<TourView>() {
//            @Override
//            public int compare(TourView o1, TourView o2) {
//                return (int) o1.getId() - o2.getId();
//            }
//        });
        tours.sort((o1, o2) -> (int) o1.getId() - o2.getId());

        request.setAttribute("tours", tours);

        logger.debug("Command finished");

        return Path.MAIN;
    }
}
