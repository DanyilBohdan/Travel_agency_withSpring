package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.db.entity.Role;
import org.bohdan.db.entity.User;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterUser extends Command {

    private static final Logger logger = Logger.getLogger(RegisterUser.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        logger.info("Log: username --> " + username);

        String login = request.getParameter("login");
        logger.info("Log: login --> " + login);

        String phone = request.getParameter("phone");
        logger.info("Log: phone --> " + phone);

        String password = request.getParameter("password");

        String password_confirm = request.getParameter("password_confirm");

        if (!password.equals(password_confirm)) {
            String errorMessage = "Password and password confirm must be exactly the same";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            request.setAttribute("password", "");
            request.setAttribute("password_confirm", "");
            return Path.ERROR_PAGE;
        }

        User user = User.createUser(username, password, login, phone, true, Role.getId("user"));
        logger.debug("Log: user --> " + user);

        boolean check = new UserDao().create(user);
        logger.debug("Log: check create user --> " + check);

        if (!check) {
            String errorMessage = "Error registration";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR_PAGE;
        }

        HttpSession session = request.getSession();

        session.setAttribute("check", "true");

        return Path.REGISTER_CHECK_REDIRECT;
    }
}
