package org.bohdan.web.services.user;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.model.Order;
import org.bohdan.model.User;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Register in tour by id user
 *
 * @author Bohdan Daniel
 */
public class RegisterTourCommand {

    private final static Logger logger = Logger.getLogger(RegisterTourCommand.class);

    public String register(HttpServletRequest request, OrderDao orderDao)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        logger.debug("Log: user --> " + user);

        int tour_id = Integer.parseInt(request.getParameter("id"));

        Date d = new Date();
        Object date = new java.sql.Timestamp(d.getTime());
        logger.debug("Log: date --> " + date);

        Order order = Order.createOrderTour("registered", date, tour_id, user.getId());

        boolean check = orderDao.create(order);
        logger.debug("Log: check create --> " + check);
        if (check) {
            session.setAttribute("check", "true");
        } else {
            session.setAttribute("check", "false");
        }

        return Path.REGISTER_CHECK_REDIRECT;
    }
}
