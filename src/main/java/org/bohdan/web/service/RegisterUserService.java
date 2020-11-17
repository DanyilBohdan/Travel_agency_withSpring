package org.bohdan.web.service;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.model.Role;
import org.bohdan.model.User;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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

@Service
public class RegisterUserService {

    private static final Logger logger = Logger.getLogger(RegisterUserService.class);

    private UserDao userDao;

    @Autowired
    public RegisterUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public ModelAndView execute(HttpServletRequest request, String nameView) throws IOException, ServletException {

        ModelAndView modelAndView = new ModelAndView(nameView);

        String username = request.getParameter("username");
        logger.info("Log: username --> " + username);
        boolean val = Validation.validateLatin(username);
        if (!val) {
            String errorMessage = "Username must be: only latin";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        String login = request.getParameter("login");
        logger.info("Log: login --> " + login);
        val = Validation.validateLogin(login);
        if (!val) {
            String errorMessage = "Login must be: example@example.com";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }
        User user = userDao.findEntityByLogin(login);
        logger.trace("user by login --> " + user);
        if (user != null) {
            String errorMessage = "This login is busy";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        String phone = request.getParameter("phone");
        logger.info("Log: phone --> " + phone);
        val = Validation.validatePhone(phone);
        if (!val) {
            String errorMessage = "Phone must be:(123) 456-7890";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        String password = request.getParameter("password");
        val = Validation.validatePassword(password);
        if (!val) {
            String errorMessage = "Password must be: latin/number";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        String password_confirm = request.getParameter("password_confirm");

        if (!password.equals(password_confirm)) {
            String errorMessage = "Password and password confirm must be exactly the same";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            request.setAttribute("password", "");
            request.setAttribute("password_confirm", "");
            return modelAndView;
        }

        User userCreate = User.createUser(username, password, login, phone, true, Role.getId("user"));
        logger.debug("Log: user --> " + userCreate);

        boolean check = userDao.create(userCreate);
        logger.debug("Log: check create user --> " + check);

        if (!check) {
            String errorMessage = "Error registration";
            request.setAttribute("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        HttpSession session = request.getSession();

        session.setAttribute("check", "true");

        return new ModelAndView(Path.REGISTER_CHECK_REDIRECT);
    }
}
