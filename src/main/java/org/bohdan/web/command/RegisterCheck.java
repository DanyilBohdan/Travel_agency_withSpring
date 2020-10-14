package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCheck extends Command{

    private static final Logger logger = Logger.getLogger(RegisterCheck.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String check = (String) request.getSession().getAttribute("check");
        logger.debug("Log: check ----> " + check);

        if (check.equals("true")) {
            request.setAttribute("checkRegistration", "Successful registration");
        } else {
            request.setAttribute("checkRegistration", "Unsuccessful registration");
        }

        return Path.PAGE_REGISTER_CHECK;
    }
}
