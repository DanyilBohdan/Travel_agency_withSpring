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
 * Update status order by id
 *
 * @author Bohdan Daniel
 */
public class UpdateStatusOrder extends Command {

    private static final Logger logger = Logger.getLogger(UpdateStatusOrder.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Log: id --> " + id);
            String status = request.getParameter("selectStatus");
            logger.info("Log: status --> " + status);

            boolean check = new OrderDao(connectionPool).updateStatus(status, id);
            logger.info("Log: check update status order --> " + check);

            return Path.COMMAND_LIST_ORDERS;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
