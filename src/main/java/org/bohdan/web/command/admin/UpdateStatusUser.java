package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.db.entity.User;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Update status user by id
 *
 * @author Bohdan Daniel
 */
public class UpdateStatusUser extends Command {

    private final static Logger logger = Logger.getLogger(SearchUser.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Log: id --> " + id);
            User user = new UserDao(connectionPool).findEntityById(id);
            boolean status;
            status = !user.getStatus();
            boolean check = new UserDao(connectionPool).updateStatus(status, id);
            logger.info("Log: check update user --> " + check);

            return Path.COMMAND_LIST_USER;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
