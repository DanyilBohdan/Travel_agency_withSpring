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
import java.util.List;

public class ViewToursCommand extends Command {

    private final static Logger logger = Logger.getLogger(ViewToursCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String lang = request.getParameter("lang");
        logger.info("LOG: localeParam = " + lang);

        HttpSession session = request.getSession();
        if (lang != null && !lang.isEmpty()) {
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
            session.setAttribute("defLocale", lang);
        } else {
            lang = (String) session.getAttribute("defLocale");
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
        }
        logger.info("LOG: localeFinal = " + lang);

        List<TourView> tours = null;
        if (lang != null) {
            tours = new TourDao().findAllByLocale(lang);
        }

        logger.trace("Found in DB: tours --> " + tours);

//        Collections.sort(tours, new Comparator<TourView>() {
//            @Override
//            public int compare(TourView o1, TourView o2) {
//                return (int) o1.getId() - o2.getId();
//            }
//        });
        tours.sort((o1, o2) -> (int) o1.getId() - o2.getId());

        request.setAttribute("tours", tours);

        logger.debug("Command finished");

        return Path.PAGE_MAIN;
    }
}
