package org.bohdan.web.services;

import org.bohdan.model.general.OrderTours;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public interface OrderService {

    List<OrderTours> viewOrders(HttpSession session) throws IOException, ServletException;

    String updateStatusOrder(Integer id, String status) throws IOException, ServletException;

    ModelAndView updateDiscountOrder(HttpServletRequest request);

    List<OrderTours> searchByStatusOrder(String lang, String status) throws IOException, ServletException;

    ModelAndView canceledOrder(HttpServletRequest request);

    ModelAndView deleteOrder(HttpServletRequest request);

    ModelAndView registerView(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView register(HttpServletRequest request) throws IOException, ServletException;
}
