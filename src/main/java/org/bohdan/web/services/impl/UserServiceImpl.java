package org.bohdan.web.services.impl;

import org.bohdan.db.DAO.OrderDao;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.web.Path;
import org.bohdan.web.services.*;
import org.bohdan.web.services.admin.AccountAdminAndManagerCommand;
import org.bohdan.web.services.user.AccountUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private OrderDao orderDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
    }

    @Override
    public ModelAndView login(HttpServletRequest request)
            throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.PAGE_LOGIN);
        return new LoginCommand().execute(request, modelAndView, userDao);
    }

    @Override
    public ModelAndView logout(HttpServletRequest request)
            throws IOException, ServletException {
        return new ModelAndView(new LogoutCommand().execute(request));
    }

    @Override
    public ModelAndView accountAdminAndManager(HttpServletRequest request, String viewName)
            throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(viewName);
        return new AccountAdminAndManagerCommand().execute(request, modelAndView);
    }

    @Override
    public ModelAndView accountUser(HttpServletRequest request)
            throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.ACCOUNT_USER);
        return new AccountUserCommand().execute(request, modelAndView, orderDao);
    }

    @Override
    public ModelAndView registerUser(HttpServletRequest request)
            throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.PAGE_REGISTER_USER);
        return new RegisterUserCommand().execute(request, modelAndView, userDao);
    }

    @Override
    public ModelAndView registerUserCheck(HttpServletRequest request)
            throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.PAGE_REGISTER_CHECK);
        return new RegisterCheck().execute(request, modelAndView);
    }

    @Override
    public ModelAndView accountEdit(HttpServletRequest request)
            throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.ERROR_PAGE);
        return new EditAccountCommand().execute(request, modelAndView, userDao);
    }

}
