package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.model.general.UserRole;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * View list users page
 *
 * @author Bohdan Daniel
 */
public class ListUsersCommand {

    private final static Logger logger = Logger.getLogger(ListUsersCommand.class);

    public ModelAndView view(ModelAndView modelAndView, UserDao userDao) throws IOException, ServletException {

        logger.debug("Command start");

        List<UserRole> users = userDao.findUsersRole();
        logger.trace("Log: users --> " + users);

        modelAndView.addObject("users", users);

        logger.debug("Command finished");

        return modelAndView;
    }
}
