package org.bohdan.web.command.user;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.db.entity.Order;
import org.bohdan.db.entity.User;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class RegisterTour extends Command {

    private final static Logger logger = Logger.getLogger(RegisterTour.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        logger.debug("Log: user --> " + user);

//        String login = request.getParameter("login");
//        logger.trace("Request parameter: login -->" + login);
//
//        String password = request.getParameter("password");
//
//        String errorMessage;
//
//        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
//            errorMessage = "Login/password cannot be empty";
//            request.setAttribute("checkRegistration", errorMessage);
//            logger.error("errorMessage --> " + errorMessage);
//            return Path.REGISTER_CHECK;
//        }
//
//        User user = new UserDao().findEntityByLogin(login);
//        logger.trace("Found in DB: user --> " + user);
//
//        if (user == null || !password.equals(user.getPassword())) {
//            errorMessage = "Cannot find user with such login/password";
//            request.setAttribute("checkRegistration", errorMessage);
//            logger.error("errorMessage --> " + errorMessage);
//            return Path.REGISTER_CHECK;
//        }

        int tour_id = Integer.parseInt(request.getParameter("id"));

        java.util.Date d = new java.util.Date();
        Object date = new java.sql.Timestamp(d.getTime());
        logger.debug("Log: date --> " + date);

        Order order = Order.createOrderTour("registered", date, tour_id, user.getId());

        boolean check = new OrderDao().create(order);
        logger.debug("Log: check create --> " + check);

        if (check) {
            request.setAttribute("checkRegistration", "Successful registration");
        } else {
            request.setAttribute("checkRegistration", "Unsuccessful registration");
        }

        return Path.REGISTER_CHECK;
    }
}
