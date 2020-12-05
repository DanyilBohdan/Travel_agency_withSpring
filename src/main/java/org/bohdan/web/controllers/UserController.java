package org.bohdan.web.controllers;

import org.apache.log4j.Logger;
import org.bohdan.model.User;
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
import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
        return userService.accountAdminAndManager(request, Path.ACCOUNT_ADMIN);
    }

    @RequestMapping(value = "manager/account", method = RequestMethod.GET)
    public ModelAndView accountManager(HttpServletRequest request) throws IOException, ServletException {
        return userService.accountAdminAndManager(request, Path.ACCOUNT_MANAGER);
    }

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public ModelAndView accountUser(HttpServletRequest request) throws IOException, ServletException {
        return userService.accountUser(request);
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
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        request.setAttribute("user", user);

        return new ModelAndView(Path.PAGE_EDIT_ACCOUNT);
    }

    @RequestMapping(value = "account/edit", method = RequestMethod.POST)
    public ModelAndView editAccount(HttpServletRequest request) throws IOException, ServletException {
        return userService.accountEdit(request);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listUsers(HttpServletRequest request) throws IOException, ServletException {
        return userService.accountEdit(request);
    }

    @RequestMapping(value = "list/search", method = RequestMethod.GET)
    public ModelAndView searchUsers(HttpServletRequest request) throws IOException, ServletException {
        return userService.searchUser(request);
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
