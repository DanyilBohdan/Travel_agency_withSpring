package org.bohdan.web.services.impl;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.model.User;
import org.bohdan.model.general.OrderTours;
import org.bohdan.model.general.TourView;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    private OrderDao orderDao;
    private TourDao  tourDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, TourDao tourDao) {
        this.orderDao = orderDao;
        this.tourDao = tourDao;
    }

    @Override
    public List<OrderTours> viewOrders(HttpSession session) throws IOException, ServletException {
        String lang = (String) session.getAttribute("localeDef");
        return orderDao.findAllOrdersLocale(lang);
    }

    @Override
    public boolean updateStatusOrder(Integer id, String status) throws IOException, ServletException {
        boolean check = orderDao.updateStatus(status, id);
        logger.info("Log: check update status order --> " + check);
        return check;
    }

    @Override
    public ModelAndView updateDiscountOrder(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(Path.COMMAND_LIST_ORDERS);
        return new UpdateDiscountOrderCommand().update(request, modelAndView, tourDao);
    }

    @Override
    public List<OrderTours> searchByStatusOrder(String lang, String status) {
        List<OrderTours> orders = orderDao.findFindByStatusOrdersUsersLocale(lang, status);
        logger.info("Found in DB: orders --> " + orders);

        return orders;
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
    public TourView registerView(String lang, Integer id, User user) throws IOException, ServletException {
//        ModelAndView modelAndView = new ModelAndView(Path.REGISTER_TOUR);
        return new RegisterTourViewCommand().register(lang, id, user, tourDao, orderDao);
    }

    @Override
    public ModelAndView register(HttpServletRequest request) throws IOException, ServletException {
        return new ModelAndView(new RegisterTourCommand().register(request, orderDao));
    }
}
