package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Delete type tour
 *
 * @author Bohdan Daniel
 */
public class DeleteType extends Command {

    private static final Logger logger = Logger.getLogger(DeleteType.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean check = new TypeTourDao(connectionPool).delete(id);
            logger.info("log: delete Type = " + check);

            return Path.COMMAND_LIST_TYPE;

        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
