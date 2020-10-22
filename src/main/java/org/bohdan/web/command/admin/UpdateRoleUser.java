package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.db.entity.Role;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Update role user by id
 *
 * @author Bohdan Daniel
 */
public class UpdateRoleUser extends Command {

    private final static Logger logger = Logger.getLogger(SearchUser.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Log: id = " + id);
            String role = request.getParameter("selectRole");
            boolean check = new UserDao(connectionPool).updateRole(Role.getId(role), id);
            logger.info("Log: check update user role --> " + check);

            return Path.COMMAND_LIST_USER;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
