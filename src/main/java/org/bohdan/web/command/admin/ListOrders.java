package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.db.bean.OrderTours;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListOrders extends Command {

    private static final Logger logger = Logger.getLogger(ListOrders.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String lang = (String) request.getSession().getAttribute("defLocale");

        List<OrderTours> orders = new OrderDao().findAllOrdersLocale(lang);

        logger.trace("Found in DB: orders --> " + orders);

        request.setAttribute("orders", orders);

        logger.debug("Command finished");

        return Path.LIST_ORDERS_ADMIN;
    }
}
