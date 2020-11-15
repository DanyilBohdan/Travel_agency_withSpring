package org.bohdan.web.service.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.model.general.UserRole;
import org.bohdan.web.Path;
import org.bohdan.web.service.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Search users by attribute user
 *
 * @author Bohdan Daniel
 */
public class SearchUser extends Command {

    private final static Logger logger = Logger.getLogger(SearchUser.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            String searchText = request.getParameter("searchText");
            String searchSelect = request.getParameter("searchSelect");
            List<UserRole> users = new UserDao(connectionPool).searchEntity(searchSelect, searchText);

            request.setAttribute("users", users);

            return Path.LIST_USERS_ADMIN;
        } catch (Exception ex){
            return Path.ERROR_PAGE;
        }
    }
}