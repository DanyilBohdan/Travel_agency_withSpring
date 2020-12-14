package org.bohdan.web.controllers;

import org.apache.log4j.Logger;
import org.bohdan.web.Path;
import org.bohdan.web.services.OrderService;
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
@RequestMapping("order")
public class OrderController {

    private static final Logger logger = Logger.getLogger(OrderController.class);

    private OrderService orderService;

    private HttpSession session;

    @Autowired
    public OrderController(OrderService orderService, HttpSession session) {
        this.orderService = orderService;
        this.session = session;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView viewOrders() throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView(Path.LIST_ORDERS_ADMIN);
        modelAndView.addObject("orders", orderService.viewOrders(session));
        return modelAndView;
    }

    @RequestMapping(value = "view/update/status", method = RequestMethod.POST)
    public ModelAndView updateStatusOrder(HttpServletRequest request) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        logger.info("Log: id ==> " + id);
        String status = request.getParameter("selectStatus");
        logger.info("Log: status ==> " + status);
        return new ModelAndView(orderService.updateStatusOrder(id, status));

    }

    @RequestMapping(value = "view/update/discount", method = RequestMethod.POST)
    public ModelAndView updateDiscountOrder(HttpServletRequest request) {
        return orderService.updateDiscountOrder(request);
    }

    @RequestMapping(value = "view/delete", method = RequestMethod.POST)
    public ModelAndView deleteOrder(HttpServletRequest request) throws IOException, ServletException {
        return orderService.deleteOrder(request);
    }

    @RequestMapping(value = "view/searchByStatus", method = RequestMethod.GET)
    public ModelAndView searchByStatusOrder(HttpServletRequest request) throws IOException, ServletException {

        String lang = (String) session.getAttribute("defLocale");

        String status = request.getParameter("searchStatus");

        ModelAndView modelAndView = new ModelAndView(Path.LIST_ORDERS_ADMIN);
        modelAndView.addObject("orders", orderService.searchByStatusOrder(lang, status));
        modelAndView.addObject("selectDef", status);

        logger.debug("Command finished");

        return modelAndView;
    }

    @RequestMapping(value = "canceled", method = RequestMethod.POST)
    public ModelAndView canceledOrder(HttpServletRequest request) {
        return orderService.canceledOrder(request);
    }

    @RequestMapping(value = "register/view", method = RequestMethod.GET)
    public ModelAndView registerView(HttpServletRequest request) throws IOException, ServletException {
        return orderService.registerView(request);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request) throws IOException, ServletException {
        return orderService.register(request);
    }
}
