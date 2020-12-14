package org.bohdan.web.controllers;

import org.apache.log4j.Logger;
import org.bohdan.model.User;
import org.bohdan.model.general.OrderTours;
import org.bohdan.web.Path;
import org.bohdan.web.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    private UserService userService;

    private HttpSession session;

    @Autowired
    public UserController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView loginUser(HttpServletRequest request) throws IOException, ServletException {
        return userService.login(request);
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logoutUser(HttpServletRequest request) throws IOException, ServletException {
        return userService.logout(request);
    }

    @RequestMapping(value = "admin/account", method = RequestMethod.GET)
    public ModelAndView accountAdmin(HttpServletRequest request) throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.ACCOUNT_ADMIN);
        String lang = request.getParameter("lang");
        logger.info("LOG: localeParam = " + lang);
        if (lang != null && !lang.isEmpty()) {
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
            session.setAttribute("defLocale", lang);
        } else {
            lang = (String) session.getAttribute("defLocale");
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
        }
        logger.info("LOG: localeFinal = " + lang);

        modelAndView.addObject("commandPage", request.getRequestURI());

        return modelAndView;
    }

    @RequestMapping(value = "manager/account", method = RequestMethod.GET)
    public ModelAndView accountManager(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Path.ACCOUNT_MANAGER);
        String lang = request.getParameter("lang");
        logger.info("LOG: localeParam = " + lang);
        if (lang != null && !lang.isEmpty()) {
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
            session.setAttribute("defLocale", lang);
        } else {
            lang = (String) session.getAttribute("defLocale");
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
        }
        logger.info("LOG: localeFinal = " + lang);

        modelAndView.addObject("commandPage", request.getRequestURI());

        return modelAndView;
    }

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public ModelAndView accountUser(HttpServletRequest request) throws IOException, ServletException {

        String lang = request.getParameter("lang");

        if (lang != null && !lang.isEmpty()) {
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
            session.setAttribute("defLocale", lang);
        } else {
            lang = (String) session.getAttribute("defLocale");
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
        }
        logger.info("LOG: localeFinal = " + lang);
        if (lang == null){
            lang = "EN";
        }
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView(Path.ACCOUNT_USER);

        List<OrderTours> ordersUser = userService.accountUser(lang, user);
        logger.info("LOG: ordersUser ==> " + ordersUser);

        modelAndView.addObject("orders", ordersUser);

        modelAndView.addObject("commandPage", request.getRequestURI());

        logger.debug("Command finished");
        return modelAndView;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView registerUserView() {
        return new ModelAndView(Path.PAGE_REGISTER_USER);
    }

    @RequestMapping(value = "registerActive", method = RequestMethod.POST)
    public ModelAndView registerUserActive(HttpServletRequest request) throws IOException, ServletException {
        return userService.registerUser(request);
    }

    @RequestMapping(value = "registerCheck", method = RequestMethod.GET)
    public ModelAndView registerUserCheck(HttpServletRequest request) throws IOException, ServletException {
        return userService.registerUserCheck(request);
    }

    @RequestMapping(value = "account/edit/view", method = RequestMethod.GET)
    public ModelAndView editAccountView(HttpServletRequest request) {

        User user = (User) session.getAttribute("user");

        request.setAttribute("user", user);

        return new ModelAndView(Path.PAGE_EDIT_ACCOUNT);
    }

    @RequestMapping(value = "account/edit", method = RequestMethod.POST)
    public ModelAndView editAccount(HttpServletRequest request) throws IOException, ServletException {
        return userService.accountEdit(request);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listUsers() {
        ModelAndView modelAndView = new ModelAndView(Path.LIST_USERS_ADMIN);
        modelAndView.addObject("users", userService.getListUsers());
        return modelAndView;
    }

    @RequestMapping(value = "list/search", method = RequestMethod.GET)
    public ModelAndView searchUsers(HttpServletRequest request) throws IOException, ServletException {
        String searchText = request.getParameter("searchText");
        String searchSelect = request.getParameter("searchSelect");
        ModelAndView modelAndView = new ModelAndView(Path.LIST_USERS_ADMIN);
        modelAndView.addObject("users", userService.searchUser(searchSelect, searchText));

        logger.debug("Command finished");

        return modelAndView;
    }

    @RequestMapping(value = "update/role", method = RequestMethod.POST)
    public ModelAndView updateRole(HttpServletRequest request) throws IOException, ServletException {
        return userService.updateRole(request);
    }

    @RequestMapping(value = "update/status", method = RequestMethod.POST)
    public ModelAndView updateStatus(HttpServletRequest request) throws IOException, ServletException {
        return userService.updateStatus(request);
    }
}
