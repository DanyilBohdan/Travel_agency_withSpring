package org.bohdan.web.service.user;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.model.Order;
import org.bohdan.model.User;
import org.bohdan.web.Path;
import org.bohdan.web.service.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Register in tour by id user
 *
 * @author Bohdan Daniel
 */
public class RegisterTour extends Command {

    private final static Logger logger = Logger.getLogger(RegisterTour.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        String lang = (String) session.getAttribute("defLocale");

        User user = (User) session.getAttribute("user");
        logger.debug("Log: user --> " + user);

        int tour_id = Integer.parseInt(request.getParameter("id"));

        java.util.Date d = new java.util.Date();
        Object date = new java.sql.Timestamp(d.getTime());
        logger.debug("Log: date --> " + date);

        Order order = Order.createOrderTour("registered", date, tour_id, user.getId());

        boolean check = new OrderDao(connectionPool).create(order);
        logger.debug("Log: check create --> " + check);
        if (check) {
            session.setAttribute("check", "true");
        } else {
            session.setAttribute("check", "false");
        }

        return Path.REGISTER_CHECK_REDIRECT;
    }
}
