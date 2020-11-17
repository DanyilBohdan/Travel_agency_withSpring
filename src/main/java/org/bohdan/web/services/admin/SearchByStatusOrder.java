package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.model.general.OrderTours;
import org.bohdan.web.Path;
import org.bohdan.web.services.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Search orders by status order
 *
 * @author Bohdan Daniel
 */
public class SearchByStatusOrder extends Command {

    private static final Logger logger = Logger.getLogger(SearchByStatusOrder.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String lang = (String) request.getSession().getAttribute("defLocale");

        String status = request.getParameter("searchStatus");

        List<OrderTours> orders = new OrderDao(connectionPool).findFindByStatusOrdersUsersLocale(lang, status);

        logger.trace("Found in DB: orders --> " + orders);

        request.setAttribute("orders", orders);
        request.setAttribute("selectDef", status);

        logger.debug("Command finished");

        return Path.LIST_ORDERS_ADMIN;
    }
}
