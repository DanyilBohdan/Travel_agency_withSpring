package org.bohdan.web.services;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.model.Role;
import org.bohdan.model.User;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Edit account command
 *
 * @author Bohdan Daniel
 */

public class EditAccountCommand {

    private static final Logger logger = Logger.getLogger(EditAccountCommand.class);

    public ModelAndView execute(HttpServletRequest request, ModelAndView modelAndView, UserDao userDao)
            throws IOException, ServletException {

        String username = request.getParameter("username");
        logger.info("Log: username --> " + username);
        boolean val = Validation.validateLatin(username);
        if (!val) {
            String errorMessage = "Username must be: only latin";
            modelAndView.addObject("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        String login = request.getParameter("login");
        logger.info("Log: login --> " + login);
        val = Validation.validateLogin(login);
        if (!val) {
            String errorMessage = "Login must be: example@example.com";
            modelAndView.addObject("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        String phone = request.getParameter("phone");
        logger.info("Log: phone --> " + phone);
        val = Validation.validatePhone(phone);
        if (!val) {
            String errorMessage = "Phone must be:(123) 456-7890";
            modelAndView.addObject("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        String password = request.getParameter("password");
        val = Validation.validatePassword(password);
        if (!val) {
            String errorMessage = "Password must be: latin/number";
            modelAndView.addObject("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            return modelAndView;
        }

        String password_confirm = request.getParameter("password_confirm");

        if (!password.equals(password_confirm)) {
            String errorMessage = "Password and password confirm must be exactly the same";
            modelAndView.addObject("errorMessage", errorMessage);
            logger.error("errorMessage --> " + errorMessage);
            modelAndView.addObject("password", "");
            modelAndView.addObject("password_confirm", "");
            return modelAndView;
        }

        HttpSession session = request.getSession();

        User userOld = (User) session.getAttribute("user");

        User user = User.createUser(username, password, login, phone, userOld.getStatus(), userOld.getRoleId());
        user.setId(userOld.getId());
        logger.info("Log: New User --> " + user);

        boolean check = userDao.update(user);
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

        return new ModelAndView(forward);
    }
}
