package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Delete tour
 *
 * @author Bohdan Daniel
 */
public class DeleteTour extends Command {

    private static final Logger logger = Logger.getLogger(DeleteTour.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean check = new TourDao(connectionPool).delete(id);
            logger.info("log: delete Tour = " + check);

            return Path.COMMAND_TOURS_ADMIN;

        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
