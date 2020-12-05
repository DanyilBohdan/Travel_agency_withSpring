package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.model.general.OrderTours;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Search orders by status order
 *
 * @author Bohdan Daniel
 */
public class SearchByStatusOrderCommand {

    private static final Logger logger = Logger.getLogger(SearchByStatusOrderCommand.class);

    public ModelAndView search(HttpServletRequest request, ModelAndView modelAndView, OrderDao orderDao)
            throws IOException, ServletException {

        String lang = (String) request.getSession().getAttribute("defLocale");

        String status = request.getParameter("searchStatus");

        List<OrderTours> orders = orderDao.findFindByStatusOrdersUsersLocale(lang, status);

        logger.trace("Found in DB: orders --> " + orders);

        modelAndView.addObject("orders", orders);
        modelAndView.addObject("selectDef", status);

        logger.debug("Command finished");

        return modelAndView;
    }
}
