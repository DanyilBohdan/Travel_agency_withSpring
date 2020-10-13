package org.bohdan.web.command.user;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.db.bean.OrderTours;
import org.bohdan.db.entity.User;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AccountUser extends Command {

    private static final Logger logger = Logger.getLogger(AccountUser.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        logger.debug("Command starts");

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        List<OrderTours> ordersUser = new OrderDao().findAllOrdersUsersLocale((String) session.getAttribute("defLocale"), user.getId());

        request.setAttribute("orders", ordersUser);

        logger.debug("Command finished");
        return Path.ACCOUNT_USER;
    }
}
