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
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * Login command
 *
 * @author Bohdan Daniel
 */

@Service
public class LoginService {

    private static final Logger logger = Logger.getLogger(LoginService.class);

    private UserDao userDao;

    @Autowired
    public LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    public ModelAndView execute(HttpServletRequest request, String nameView) throws IOException, ServletException {
        logger.debug("Command starts");

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        logger.trace("Request parameter: login -->" + login);

        String password = request.getParameter("password");

        ModelAndView modelAndView = new ModelAndView(nameView);
        String errorMessage;
        String forward = Path.PAGE_LOGIN;

        boolean val = Validation.validateLogin(login);
        if (!val) {
            errorMessage = "Login must be: example@example.com";
            modelAndView.addObject("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        val = Validation.validatePassword(password);
        if (!val) {
            errorMessage = "Password must be: latin/number";
            modelAndView.addObject("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            modelAndView.addObject("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        User user = userDao.findEntityByLogin(login);
        logger.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            modelAndView.addObject("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }
        if (!user.getStatus()) {
            errorMessage = "Your account has been blocked!";
            modelAndView.addObject("errorVal", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        } else {
            Role role = Role.getRole(user);
            logger.trace("role --> " + role);

            if (role == Role.ADMIN) {
                modelAndView = new ModelAndView(Path.COMMAND_ACCOUNT_ADMIN);
            }

            if (role == Role.MANAGER) {
                modelAndView = new ModelAndView(Path.COMMAND_ACCOUNT_MANAGER);
            }

            if (role == Role.USER) {
                modelAndView = new ModelAndView(Path.COMMAND_ACCOUNT);
            }

            session.setAttribute("user", user);
            logger.trace("Set the session attribute: user --> " + user);

            session.setAttribute("userRole", role);
            logger.trace("Set the session attribute: userRole --> " + role);

            logger.info("User " + user + " logged as " + role.toString().toLowerCase());

            String defLocale = (String) Config.get(session, "javax.servlet.jsp.jstl.fmt.locale");
            logger.trace("LOG: defLocale = " + defLocale);

            session.setAttribute("localeDef", defLocale);
        }

        logger.debug("Command finished");
        return modelAndView;
    }
}
