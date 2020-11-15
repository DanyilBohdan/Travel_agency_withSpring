package org.bohdan.web.service.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.model.general.OrderTours;
import org.bohdan.web.Path;
import org.bohdan.web.service.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * View list orders page
 *
 * @author Bohdan Daniel
 */
public class ListOrders extends Command {

    private static final Logger logger = Logger.getLogger(ListOrders.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String lang = (String) request.getSession().getAttribute("defLocale");

        List<OrderTours> orders = new OrderDao(connectionPool).findAllOrdersLocale(lang);

        logger.trace("Found in DB: orders --> " + orders);

        request.setAttribute("orders", orders);

        logger.debug("Command finished");

        return Path.LIST_ORDERS_ADMIN;
    }
}
