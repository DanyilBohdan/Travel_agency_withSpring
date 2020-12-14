package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.model.User;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Update status user by id
 *
 * @author Bohdan Daniel
 */
public class UpdateStatusUserCommand {

    private final static Logger logger = Logger.getLogger(UpdateStatusUserCommand.class);

    public String update(HttpServletRequest request, UserDao userDao) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Log: id --> " + id);
            User user = userDao.findEntityById(id);
            boolean status;
            status = !user.getStatus();
            boolean check = userDao.updateStatus(status, id);
            logger.info("Log: check update user --> " + check);

            return Path.COMMAND_LIST_USER;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
