package org.bohdan.web.services.impl;

import org.bohdan.db.DAO.OrderDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.web.Path;
import org.bohdan.web.services.OrderService;
import org.bohdan.web.services.admin.*;
import org.bohdan.web.services.user.CanceledOrderCommand;
import org.bohdan.web.services.user.RegisterTourCommand;
import org.bohdan.web.services.user.RegisterTourViewCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private TourDao  tourDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, TourDao tourDao) {
        this.orderDao = orderDao;
        this.tourDao = tourDao;
    }

    @Override
    public ModelAndView viewOrders(HttpServletRequest request) throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.LIST_ORDERS_ADMIN);
        return new ListOrdersCommand().view(request, modelAndView, orderDao);
    }

    @Override
    public ModelAndView updateStatusOrder(HttpServletRequest request) throws IOException, ServletException {
        return new ModelAndView(new UpdateStatusOrderCommand().update(request, orderDao));
    }

    @Override
    public ModelAndView updateDiscountOrder(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Path.COMMAND_LIST_ORDERS);
        return new UpdateDiscountOrderCommand().update(request, modelAndView, tourDao);
    }

    @Override
    public ModelAndView searchByStatusOrder(HttpServletRequest request) throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.LIST_ORDERS_ADMIN);
        return new SearchByStatusOrderCommand().search(request, modelAndView, orderDao);
    }

    @Override
    public ModelAndView canceledOrder(HttpServletRequest request) {
        return new ModelAndView(new CanceledOrderCommand().canceled(request, orderDao));
    }

    @Override
    public ModelAndView deleteOrder(HttpServletRequest request) {
        return new ModelAndView(new DeleteOrderCommand().delete(request, orderDao));
    }

    @Override
    public ModelAndView registerView(HttpServletRequest request) throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.REGISTER_TOUR);
        return new RegisterTourViewCommand().register(request, modelAndView, tourDao, orderDao);
    }

    @Override
    public ModelAndView register(HttpServletRequest request) throws IOException, ServletException {
        return new ModelAndView(new RegisterTourCommand().register(request, orderDao));
    }
}
