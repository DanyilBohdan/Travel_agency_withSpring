package org.bohdan.web.command.user;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.bean.OrderToursByIdUser;
import org.bohdan.db.bean.TourView;
import org.bohdan.db.entity.Tour;
import org.bohdan.db.entity.User;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class RegisterTourView extends Command {

    private final static Logger logger = Logger.getLogger(RegisterTourView.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            String lang = (String) request.getSession().getAttribute("defLocale");
            logger.debug("Log: lang -->" + lang);


            Locale current = new Locale(lang);
            String errorMessage;

            int id = Integer.parseInt(request.getParameter("id"));
            TourView tour = new TourDao().findByIdLocale(lang, id);

            //check on count people
            Integer count_peopleForTour = new OrderDao().findCountOrderByIdTour(id);
            if (count_peopleForTour >= tour.getCount_people()) {
                errorMessage = ResourceBundle.getBundle("resources", current).getString("registrationTour.noAvailable");
                request.setAttribute("noAvailable", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
            } else {

                //check on date
                User user = (User) request.getSession().getAttribute("user");
                List<OrderToursByIdUser> ordersUser = new OrderDao().findToursByIdUser(user.getId());

                if (!checkDate(ordersUser, tour)) {
                    errorMessage = ResourceBundle.getBundle("resources", current).getString("registrationTour.errorDate");
                    request.setAttribute("noAvailable", errorMessage);
                    logger.error("errorMessage --> " + errorMessage);
                }
            }

            request.setAttribute("tour", tour);

            return Path.REGISTER_TOUR;

        } catch (Exception ex) {
            logger.error("Log: ex --> " + ex);
            return Path.ERROR_PAGE;
        }
    }

    private boolean checkDate(List<OrderToursByIdUser> ordersUser, TourView tour) {
        boolean res = true;

        Date startTour = tour.getStart_date();
        int daysTour = tour.getDays();

        Calendar c = Calendar.getInstance();
        c.setTime(startTour);

        c.add(Calendar.DATE, daysTour + 2);
        Date lastDateTour = c.getTime();
        logger.info("Log: Tour ==> startDate --> " + startTour + " -> days --> " +
                daysTour + " -> lastDate --> " + lastDateTour);

        for (OrderToursByIdUser orderToursByIdUser : ordersUser) {
            Date startOrder = orderToursByIdUser.getStart_date();
            int daysOrder = orderToursByIdUser.getDays();
            Calendar cOrder = Calendar.getInstance();
            cOrder.setTime(startOrder);

            cOrder.add(Calendar.DATE, daysOrder + 2);
            Date lastDateOrder = cOrder.getTime();
            logger.info("Log: Order ==> startDate --> " + startOrder + " -> days --> " +
                    daysOrder + " -> lastDate --> " + lastDateOrder);

            if (startTour.before(lastDateOrder) || startOrder.before(lastDateTour) || startOrder.equals(startTour)) {
                res = false;
                break;
            }
        }
        return res;
    }
}
