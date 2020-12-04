package org.bohdan.web.services;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * View register check
 *
 * @author Bohdan Daniel
 */

public class RegisterCheck {

    private static final Logger logger = Logger.getLogger(RegisterCheck.class);

    public ModelAndView execute(HttpServletRequest request, ModelAndView modelAndView) throws IOException, ServletException {

        HttpSession session = request.getSession();

        String check = (String) session.getAttribute("check");
        String lang = (String) session.getAttribute("defLocale");
        logger.debug("Log: check ----> " + check);

        if (check.equals("true")) {
            modelAndView.addObject("checkRegistration", ResourceBundle.getBundle("resources", new Locale(lang)).getString("registrationTour.successful"));
        } else {
            modelAndView.addObject("checkRegistration", ResourceBundle.getBundle("resources", new Locale(lang)).getString("registrationTour.unsuccessful"));
        }

        return modelAndView;
    }
}
