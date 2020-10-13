package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.db.bean.UserRole;
import org.bohdan.db.entity.Role;
import org.bohdan.db.entity.User;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListUsers extends Command {

    private final static Logger logger = Logger.getLogger(ListUsers.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        logger.debug("Command start");

        List<UserRole> users = new UserDao().findUsersRole();
        logger.trace("Log: users --> " + users);

        request.setAttribute("users", users);

        logger.debug("Command finished");

        return Path.LIST_USERS_ADMIN;
    }
}
