package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.bean.TourView;
import org.bohdan.db.entity.Tour;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateDiscountOrder extends Command {

    private final static Logger logger = Logger.getLogger(UpdateDiscountOrder.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Log: id --> " + id);
            float discount = Float.parseFloat(request.getParameter("discount"));
            logger.info("Log: discount : " + discount);
            if (!Validation.validateFloat(discount, 0, 1)) {
                String checkVal = "Discount must be: only numbers from 0 - 1";
                request.setAttribute("errorMessage", checkVal);
                logger.error("errorMessage --> " + checkVal);
                return Path.ERROR_PAGE;

            }

            Tour tour = new TourDao().findIDTour(id);

            boolean check = new TourDao().updateDiscount(discount, tour.getPrice(), id);
            logger.info("Log: check update discount order --> " + check);

            return Path.COMMAND_LIST_ORDERS;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
