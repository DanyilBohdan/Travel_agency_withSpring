package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Update status order by id
 *
 * @author Bohdan Daniel
 */
public class UpdateStatusOrderCommand {

    private static final Logger logger = Logger.getLogger(UpdateStatusOrderCommand.class);

    public String update(HttpServletRequest request, OrderDao orderDao) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Log: id --> " + id);
            String status = request.getParameter("selectStatus");
            logger.info("Log: status --> " + status);

            boolean check = orderDao.updateStatus(status, id);
            logger.info("Log: check update status order --> " + check);

            return Path.COMMAND_LIST_ORDERS;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
