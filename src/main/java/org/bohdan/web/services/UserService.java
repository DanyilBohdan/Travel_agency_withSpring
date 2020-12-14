package org.bohdan.web.services;

import org.bohdan.model.User;
import org.bohdan.model.general.OrderTours;
import org.bohdan.model.general.UserRole;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface UserService {

    ModelAndView login(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView logout(HttpServletRequest request) throws IOException, ServletException;

    List<OrderTours> accountUser(String lang, User user) throws IOException, ServletException;

    ModelAndView registerUser(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView registerUserCheck(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView accountEdit(HttpServletRequest request) throws IOException, ServletException;

    List<UserRole> getListUsers();

    List<UserRole> searchUser(String searchSelect, String searchText) throws IOException, ServletException;

    ModelAndView updateRole(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView updateStatus(HttpServletRequest request) throws IOException, ServletException;
}