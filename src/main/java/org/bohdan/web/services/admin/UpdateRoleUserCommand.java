package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.model.Role;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Update role user by id
 *
 * @author Bohdan Daniel
 */
public class UpdateRoleUserCommand {

    private final static Logger logger = Logger.getLogger(UpdateRoleUserCommand.class);

    public String update(HttpServletRequest request, UserDao userDao) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            logger.info("Log: id = " + id);
            String role = request.getParameter("selectRole");
            boolean check = userDao.updateRole(Role.getId(role), id);
            logger.info("Log: check update user role --> " + check);

            return Path.COMMAND_LIST_USER;
        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
