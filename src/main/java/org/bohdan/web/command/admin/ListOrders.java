package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.bean.TourView;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListOrders extends Command {

    private static final Logger logger = Logger.getLogger(ListOrders.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//        List<TourView> tours = new TourDao().findAllByLocale("EN");
//
//        logger.trace("Found in DB: tours --> " + tours);
//
//        tours.sort((o1, o2) -> (int) o1.getId() - o2.getId());
//
//        request.setAttribute("tours", tours);
//
//        request.setAttribute("check_login", Path.LOGIN_CHECK);
//
//        request.setAttribute("pageMain", Path.LIST_TOURS);
//
//        logger.debug("Command finished");
//
//        return Path.LIST_TOURS_ADMIN;
        return null;
    }
}