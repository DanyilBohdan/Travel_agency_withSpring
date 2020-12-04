package org.bohdan.web.services;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface OrderService {

    ModelAndView viewOrders(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView updateStatusOrder(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView updateDiscountOrder(HttpServletRequest request);

    ModelAndView canceledOrder(HttpServletRequest request);

    ModelAndView deleteOrder(HttpServletRequest request);
}
