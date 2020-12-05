package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Update discount order by id order
 *
 * @author Bohdan Daniel
 */
public class UpdateDiscountOrderCommand {

    private final static Logger logger = Logger.getLogger(UpdateDiscountOrderCommand.class);

    public ModelAndView update(HttpServletRequest request, ModelAndView modelAndView, TourDao tourDao) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Log: id --> " + id);
            float discount = Float.parseFloat(request.getParameter("discount"));
            logger.info("Log: discount : " + discount);
            if (!Validation.validateFloat(discount, 0, 1)) {
                String checkVal = "Discount must be: only numbers from 0 - 1";
                modelAndView.addObject("errorMessage", checkVal);
                logger.error("errorMessage --> " + checkVal);
                return new ModelAndView(Path.ERROR_PAGE);
            }

            boolean check = tourDao.updateDiscount(discount, id);
            logger.info("Log: check update discount order --> " + check);

            return modelAndView;
        } catch (Exception ex) {
            return new ModelAndView(Path.ERROR_PAGE);
        }
    }
}
