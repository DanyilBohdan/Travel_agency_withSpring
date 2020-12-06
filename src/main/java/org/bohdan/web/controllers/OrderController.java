package org.bohdan.web.controllers;

import org.bohdan.web.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView viewOrders(HttpServletRequest request) throws IOException, ServletException {
        return orderService.viewOrders(request);
    }

    @RequestMapping(value = "view/update/status", method = RequestMethod.POST)
    public ModelAndView updateStatusOrder(HttpServletRequest request) throws IOException, ServletException {
        return orderService.updateStatusOrder(request);
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
        return orderService.searchByStatusOrder(request);
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
