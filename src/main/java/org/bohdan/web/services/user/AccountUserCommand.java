package org.bohdan.web.services.user;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.model.general.OrderTours;
import org.bohdan.model.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;

/**
 * View account user
 *
 * @author Bohdan Daniel
 */
public class AccountUserCommand {

    private static final Logger logger = Logger.getLogger(AccountUserCommand.class);

    public ModelAndView execute(HttpServletRequest request, ModelAndView modelAndView, OrderDao orderDao) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");

        if (lang != null && !lang.isEmpty()) {
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
            session.setAttribute("defLocale", lang);
        } else {
            lang = (String) session.getAttribute("defLocale");
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
        }
        logger.info("LOG: localeFinal = " + lang);

        User user = (User) session.getAttribute("user");

        List<OrderTours> ordersUser = orderDao.findAllOrdersUsersLocale((String) session.getAttribute("defLocale"), user.getId());

        modelAndView.addObject("orders", ordersUser);

        modelAndView.addObject("commandPage", request.getRequestURI());

        logger.debug("Command finished");
        return modelAndView;
    }
}
