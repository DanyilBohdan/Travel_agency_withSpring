package org.bohdan.web.controllers;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.web.Path;
import org.bohdan.web.service.*;
import org.bohdan.web.service.admin.AccountAdminService;
import org.bohdan.web.service.manager.AccountManagerService;
import org.bohdan.web.service.user.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    private LoginService loginService;
    private AccountUserService accountUserService;
    private AccountAdminService accountAdminService;
    private AccountManagerService accountManagerService;
    private RegisterUserService registerUserService;
    private RegisterCheck registerCheck;

    @Autowired
    public UserController(LoginService loginService, AccountUserService accountUserService,
                          AccountAdminService accountAdminService, AccountManagerService accountManagerService,
                          RegisterUserService registerUserService, RegisterCheck registerCheck) {
        this.loginService = loginService;
        this.accountUserService = accountUserService;
        this.accountAdminService = accountAdminService;
        this.accountManagerService = accountManagerService;
        this.registerUserService = registerUserService;
        this.registerCheck = registerCheck;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView loginUser(HttpServletRequest request) throws IOException, ServletException {
        return loginService.execute(request, Path.PAGE_LOGIN);
    }

    @RequestMapping(value = "admin/account", method = RequestMethod.POST)
    public ModelAndView accountAdmin(HttpServletRequest request) throws IOException, ServletException {
        return accountAdminService.execute(request, Path.ACCOUNT_ADMIN);
    }

    @RequestMapping(value = "manager/account", method = RequestMethod.POST)
    public ModelAndView accountManager(HttpServletRequest request) throws IOException, ServletException {
        return accountManagerService.execute(request, Path.ACCOUNT_MANAGER);
    }

    @RequestMapping(value = "account", method = RequestMethod.POST)
    public ModelAndView accountUser(HttpServletRequest request) throws IOException, ServletException {
        return accountUserService.execute(request, Path.ACCOUNT_USER);
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView registerUserView() {
        return new ModelAndView(Path.PAGE_REGISTER_USER);
    }

    @RequestMapping(value = "registerActive", method = RequestMethod.POST)
    public ModelAndView registerUserActive(HttpServletRequest request) throws IOException, ServletException {
        return registerUserService.execute(request, Path.PAGE_REGISTER_USER);
    }

    @RequestMapping(value = "registerCheck", method = RequestMethod.GET)
    public ModelAndView registerUserCheck(HttpServletRequest request) throws IOException, ServletException {
        return registerCheck.execute(request, Path.PAGE_REGISTER_CHECK);
    }
}
