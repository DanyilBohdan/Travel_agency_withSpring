package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.model.general.UserRole;
import org.bohdan.web.Path;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Search users by attribute user
 *
 * @author Bohdan Daniel
 */
public class SearchUserCommand {

    private final static Logger logger = Logger.getLogger(SearchUserCommand.class);

    public ModelAndView search(HttpServletRequest request, ModelAndView modelAndView, UserDao userDao) throws IOException, ServletException {
        try{
            logger.debug("Command start");
            String searchText = request.getParameter("searchText");
            String searchSelect = request.getParameter("searchSelect");
            List<UserRole> users = userDao.searchEntity(searchSelect, searchText);

            modelAndView.addObject("users", users);

            logger.debug("Command finished");

            return modelAndView;
        } catch (Exception ex){
            return new ModelAndView(Path.ERROR_PAGE);
        }
    }
}