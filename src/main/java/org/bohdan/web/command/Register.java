package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * View page register user
 *
 * @author Bohdan Daniel
 */
public class Register extends Command{

    private static final Logger logger = Logger.getLogger(Register.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        logger.info("Command start");

        logger.info("Command finish");

        return Path.PAGE_REGISTER_USER;
    }
}
