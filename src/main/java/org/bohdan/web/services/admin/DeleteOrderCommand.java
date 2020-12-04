package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.web.Path;
import org.bohdan.web.services.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Delete order
 *
 * @author Bohdan Daniel
 */
public class DeleteOrderCommand {

    private static final Logger logger = Logger.getLogger(DeleteOrderCommand.class);

    public String delete(HttpServletRequest request, OrderDao orderDao) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("log: delete Tour by ID = " + id);
            boolean check = orderDao.delete(id);
            logger.info("log: delete Tour = " + check);

            return Path.COMMAND_LIST_ORDERS;

        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
