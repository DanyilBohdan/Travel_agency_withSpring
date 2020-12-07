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
 * View list orders page
 *
 * @author Bohdan Daniel
 */
public class ListOrdersCommand {

    private static final Logger logger = Logger.getLogger(ListOrdersCommand.class);

    public ModelAndView view(HttpServletRequest request, ModelAndView modelAndView, OrderDao orderDao)
            throws IOException, ServletException {

        String lang = (String) request.getSession().getAttribute("localeDef");

        List<OrderTours> orders = orderDao.findAllOrdersLocale(lang);

        logger.trace("Found in DB: orders --> " + orders);

        modelAndView.addObject("orders", orders);

        logger.debug("Command finished");

        return modelAndView;
    }
}
