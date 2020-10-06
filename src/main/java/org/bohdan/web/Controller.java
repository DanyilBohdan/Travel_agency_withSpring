package org.bohdan.web;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.web.command.Command;
import org.bohdan.web.command.CommandContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SecurityException, ServletException {
        logger.debug("Controller starts");

        String commandName = request.getParameter("command");
        logger.trace("Request parameter : command --> " + commandName);

        Command command = CommandContainer.get(commandName);
        logger.trace("Obtained command --> " + command);

        String forward = command.execute(request, response);
        logger.trace("Forward address --> " + forward);

        logger.debug("Controller finished, now go to forward address --> " + forward);

        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }
    }
}
