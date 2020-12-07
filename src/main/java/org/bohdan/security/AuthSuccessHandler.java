package org.bohdan.security;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.model.Role;
import org.bohdan.model.User;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;
import org.bohdan.web.services.common.LoginCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = Logger.getLogger(AuthSuccessHandler.class);

    private UserDao userDao;

    @Autowired
    public AuthSuccessHandler(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        logger.info("LOG: authentication ===>>" + authentication.getPrincipal());

        HttpSession session = request.getSession();

        String login = request.getParameter("username");
        logger.info("Request parameter: login -->" + login);

        String password = request.getParameter("password");

        String errorMessage;
        String url = "/login";

        User user = userDao.findEntityByLogin(login);
        logger.info("Found in DB: user --> " + user);

        Role role = Role.getRole(user);
        logger.info("role --> " + role);

        if (role == Role.ADMIN) {
            url = Path.REDIRECT_ACCOUNT_ADMIN;
        }

        if (role == Role.MANAGER) {
            url = Path.REDIRECT_ACCOUNT_MANAGER;
        }

        if (role == Role.USER) {
            url = Path.REDIRECT_ACCOUNT;
        }

        session.setAttribute("user", user);
        logger.info("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", role);
        logger.info("Set the session attribute: userRole --> " + role);

        logger.info("User " + user + " logged as " + role.toString().toLowerCase());

        String defLocale = (String) Config.get(session, "javax.servlet.jsp.jstl.fmt.locale");
        logger.info("LOG: defLocale = " + defLocale);
        if (defLocale == null) {
            defLocale = "EN";
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", defLocale);
        }
        session.setAttribute("localeDef", defLocale);
        response.sendRedirect(url);
    }
}