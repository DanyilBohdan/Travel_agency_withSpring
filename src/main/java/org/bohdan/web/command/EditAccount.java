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

public class EditAccount extends Command {

    private static final Logger logger = Logger.getLogger(EditAccount.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        logger.info("Log: username --> " + username);
        boolean val = Validation.validateLatin(username);
        if (!val) {
            String errorMessage = "Username must be: only latin";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR_PAGE;
        }

        String login = request.getParameter("login");
        logger.info("Log: login --> " + login);
        val = Validation.validateLogin(login);
        if (!val) {
            String errorMessage = "Login must be: example@example.com";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR_PAGE;
        }

        String phone = request.getParameter("phone");
        logger.info("Log: phone --> " + phone);
        val = Validation.validatePhone(phone);
        if (!val) {
            String errorMessage = "Phone must be:(123) 456-7890";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR_PAGE;
        }

        String password = request.getParameter("password");
        val = Validation.validatePassword(password);
        if (!val) {
            String errorMessage = "Password must be: latin/number";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return Path.ERROR_PAGE;
        }

        String password_confirm = request.getParameter("password_confirm");

        if (!password.equals(password_confirm)) {
            String errorMessage = "Password and password confirm must be exactly the same";
            request.setAttribute("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            request.setAttribute("password", "");
            request.setAttribute("password_confirm", "");
            return Path.ERROR_PAGE;
        }

        HttpSession session = request.getSession();

        User userOld = (User) session.getAttribute("user");

        User user = User.createUser(username, password, login, phone, userOld.getStatus(), userOld.getRole_id());
        user.setId(userOld.getId());
        logger.info("Log: New User --> " + user);

        boolean check = new UserDao(connectionPool).update(user);
        logger.info("Log: check update User --> " + check);
        if (check){
            session.setAttribute("user", user);
        }

        Role role = Role.getRole(user);
        logger.trace("role --> " + role);

        String forward = null;

        if (role == Role.ADMIN) {
            forward = Path.COMMAND_ACCOUNT_ADMIN;
        }

        if (role == Role.MANAGER) {
            forward = Path.COMMAND_ACCOUNT_MANAGER;
        }

        if (role == Role.USER) {
            forward = Path.COMMAND_ACCOUNT;
        }
        return forward;
    }
}
