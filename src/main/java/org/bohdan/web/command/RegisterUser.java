package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.db.entity.Role;
import org.bohdan.db.entity.User;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Register User
 *
 * @author Bohdan Daniel
 */
public class RegisterUser extends Command {

    private static final Logger logger = Logger.getLogger(RegisterUser.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        logger.info("Log: username --> " + username);
        boolean val = Validation.validateLatin(username);
        if (!val) {
            String errorMessage = "Username must be: only latin";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.PAGE_REGISTER_USER;
        }

        String login = request.getParameter("login");
        logger.info("Log: login --> " + login);
        val = Validation.validateLogin(login);
        if (!val) {
            String errorMessage = "Login must be: example@example.com";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.PAGE_REGISTER_USER;
        }
        User user = new UserDao(connectionPool).findEntityByLogin(login);
        logger.trace("user by login --> " + user);
        if (user != null) {
            String errorMessage = "This login is busy";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.PAGE_REGISTER_USER;
        }

        String phone = request.getParameter("phone");
        logger.info("Log: phone --> " + phone);
        val = Validation.validatePhone(phone);
        if (!val) {
            String errorMessage = "Phone must be:(123) 456-7890";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.PAGE_REGISTER_USER;
        }

        String password = request.getParameter("password");
        val = Validation.validatePassword(password);
        if (!val) {
            String errorMessage = "Password must be: latin/number";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.PAGE_REGISTER_USER;
        }

        String password_confirm = request.getParameter("password_confirm");

        if (!password.equals(password_confirm)) {
            String errorMessage = "Password and password confirm must be exactly the same";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            request.setAttribute("password", "");
            request.setAttribute("password_confirm", "");
            return Path.PAGE_REGISTER_USER;
        }

        User userCreate = User.createUser(username, password, login, phone, true, Role.getId("user"));
        logger.debug("Log: user --> " + userCreate);

        boolean check = new UserDao(connectionPool).create(userCreate);
        logger.debug("Log: check create user --> " + check);

        if (!check) {
            String errorMessage = "Error registration";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.PAGE_REGISTER_USER;
        }

        HttpSession session = request.getSession();

        session.setAttribute("check", "true");

        return Path.REGISTER_CHECK_REDIRECT;
    }
}
