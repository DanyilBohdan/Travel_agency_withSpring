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
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

public class LoginCommand extends Command {

    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.debug("Command starts");

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        logger.trace("Request parameter: login -->" + login);

        String password = request.getParameter("password");

        String errorMessage;
        String forward = Path.ERROR_PAGE;

        boolean val = Validation.validateLogin(login);
        if (!val) {
            errorMessage = "Login must be: example@example.com";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return forward;
        }

        val = Validation.validatePassword(password);
        if (!val) {
            errorMessage = "Password must be: latin/number";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR_PAGE;
        }

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return forward;
        }

        User user = new UserDao().findEntityByLogin(login);
        logger.trace("Found in DB: user --> " + user);

        logger.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return forward;
        }
        if (!user.getStatus()) {
            errorMessage = "Your account has been blocked!";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            Role role = Role.getRole(user);
            logger.trace("role --> " + role);

            if (role == Role.ADMIN) {
                forward = Path.COMMAND_ACCOUNT_ADMIN;
            }

            if (role == Role.MANAGER) {
                forward = Path.COMMAND_ACCOUNT_MANAGER;
            }

            if (role == Role.USER) {
                forward = Path.COMMAND_ACCOUNT;
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
        return forward;
    }
}
