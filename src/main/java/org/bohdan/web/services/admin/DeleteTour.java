package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.web.Path;
import org.bohdan.web.services.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Delete tour
 *
 * @author Bohdan Daniel
 */

public class DeleteTour {

    private static final Logger logger = Logger.getLogger(DeleteTour.class);

    public String execute(int id, TourDao tourDao) {
        try {
            boolean check = tourDao.delete(id);
            logger.info("log: delete Tour = " + check);

            return Path.COMMAND_TOURS_ADMIN;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
