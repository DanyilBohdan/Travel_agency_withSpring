package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * View register check
 *
 * @author Bohdan Daniel
 */
public class RegisterCheck extends Command{

    private static final Logger logger = Logger.getLogger(RegisterCheck.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        String check = (String) session.getAttribute("check");
        String lang = (String) session.getAttribute("defLocale");
        logger.debug("Log: check ----> " + check);

        if (check.equals("true")) {
            request.setAttribute("checkRegistration", ResourceBundle.getBundle("resources", new Locale(lang)).getString("registrationTour.successful"));
        } else {
            request.setAttribute("checkRegistration", ResourceBundle.getBundle("resources", new Locale(lang)).getString("registrationTour.unsuccessful"));
        }

        return Path.PAGE_REGISTER_CHECK;
    }
}
