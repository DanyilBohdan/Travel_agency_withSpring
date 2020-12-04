package org.bohdan.web.services;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface UserService {

    ModelAndView login(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView logout(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView accountAdminAndManager(HttpServletRequest request, String viewName) throws IOException, ServletException;

    ModelAndView accountUser(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView registerUser(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView registerUserCheck(HttpServletRequest request) throws IOException, ServletException;

    ModelAndView accountEdit(HttpServletRequest request) throws IOException, ServletException;
}
