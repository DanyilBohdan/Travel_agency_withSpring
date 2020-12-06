package org.bohdan.web.services.user;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.model.general.OrderToursByIdUser;
import org.bohdan.model.general.TourView;
import org.bohdan.model.User;
import org.bohdan.web.Path;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * View register page and check logic validation
 *
 * @author Bohdan Daniel
 */
public class RegisterTourViewCommand {

    private final static Logger logger = Logger.getLogger(RegisterTourViewCommand.class);

    public ModelAndView register(HttpServletRequest request, ModelAndView modelAndView,
                          TourDao tourDao, OrderDao orderDao)
            throws IOException, ServletException {
        try {
            String lang = (String) request.getSession().getAttribute("defLocale");
            logger.debug("Log: lang -->" + lang);

            Locale current = new Locale(lang);
            String errorMessage = null;

            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("LOG: id --> " + id);
            TourView tour = tourDao.findByIdLocale(lang, id);
            logger.info("LOG: tour --> " + tour);

            //check on count people
            Integer count_peopleForTour = orderDao.findCountOrderByIdTour(id);
            logger.info("LOG: count_peopleForTour --> " + count_peopleForTour);
            if (count_peopleForTour >= tour.getCountPeople()) {
                errorMessage = ResourceBundle.getBundle("resources", current).getString("registrationTour.noAvailable");
                modelAndView.addObject("noAvailable", errorMessage);
                logger.error("errorMessage --> " + errorMessage);
            } else {

                //check on date
                User user = (User) request.getSession().getAttribute("user");
                logger.debug("LOG: user --> " + user);
                List<OrderToursByIdUser> ordersUser = orderDao.findToursByIdUser(user.getId());

                if (!checkDate(ordersUser, tour)) {
                    errorMessage = ResourceBundle.getBundle("resources", current).getString("registrationTour.errorDate");
                    modelAndView.addObject("noAvailable", errorMessage);
                    logger.error("errorMessage --> " + errorMessage);
                }
            }

            modelAndView.addObject("tour", tour);

            return modelAndView;

        } catch (Exception ex) {
            logger.error("Log: ex --> " + ex);
            return new ModelAndView(Path.ERROR_PAGE);
        }
    }

    private boolean checkDate(List<OrderToursByIdUser> ordersUser, TourView tour) {
        boolean res = true;

        Date startTour = tour.getStartDate();
        int daysTour = tour.getDays();

        Calendar c = Calendar.getInstance();
        c.setTime(startTour);

        c.add(Calendar.DATE, daysTour + 2);
        Date lastDateTour = c.getTime();
        logger.info("Log: Tour ==> startDate --> " + startTour + " -> days --> " +
                daysTour + " -> lastDate --> " + lastDateTour);

        for (OrderToursByIdUser orderToursByIdUser : ordersUser) {
            Date startOrder = orderToursByIdUser.getStartDate();
            int daysOrder = orderToursByIdUser.getDays();
            Calendar cOrder = Calendar.getInstance();
            cOrder.setTime(startOrder);

            cOrder.add(Calendar.DATE, daysOrder + 2);
            Date lastDateOrder = cOrder.getTime();
            logger.info("Log: Order ==> startDate --> " + startOrder + " -> days --> " +
                    daysOrder + " -> lastDate --> " + lastDateOrder);

            if (startTour.before(startOrder) && lastDateTour.after(startOrder)) {
                res = false;
                logger.info("Log: first");
                break;
            }
            if (startOrder.before(lastDateTour) && lastDateOrder.after(lastDateTour)) {
                res = false;
                logger.info("Log: second");
                break;
            }
        }
        return res;
    }
}
