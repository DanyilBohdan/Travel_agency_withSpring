package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;
import org.bohdan.web.services.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Update discount order by id order
 *
 * @author Bohdan Daniel
 */
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

            boolean check = new TourDao(connectionPool).updateDiscount(discount, id);
            logger.info("Log: check update discount order --> " + check);

            return Path.COMMAND_LIST_ORDERS;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
