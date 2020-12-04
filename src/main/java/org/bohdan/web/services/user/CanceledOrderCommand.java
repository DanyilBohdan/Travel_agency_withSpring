package org.bohdan.web.services.user;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command canceled order
 *
 * @author Bohdan Daniel
 */
public class CanceledOrderCommand {

    private final static Logger logger = Logger.getLogger(CanceledOrderCommand.class);

    public String canceled(HttpServletRequest request, OrderDao orderDao) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Log: id --> " + id);
            String canceled = request.getParameter("canceled");
            logger.info("Log: canceled --> " + canceled);

            boolean check = orderDao.updateStatus(canceled, id);
            logger.info("Log: check update status order --> " + check);

            return Path.COMMAND_ACCOUNT;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
