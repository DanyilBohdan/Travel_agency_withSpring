package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrder extends Command {

    private static final Logger logger = Logger.getLogger(DeleteOrder.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean check = new OrderDao().delete(id);
            logger.info("log: delete Tour = " + check);

            return Path.COMMAND_LIST_ORDERS;

        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
