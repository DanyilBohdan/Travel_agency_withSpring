package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.web.Path;

/**
 * Delete tour
 *
 * @author Bohdan Daniel
 */

public class DeleteTourCommand {

    private static final Logger logger = Logger.getLogger(DeleteTourCommand.class);

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
