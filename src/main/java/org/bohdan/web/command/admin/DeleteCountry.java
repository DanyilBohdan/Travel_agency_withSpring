package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCountry extends Command {

    private static final Logger logger = Logger.getLogger(DeleteCountry.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean check = new CountryDao(connectionPool).delete(id);
            logger.info("log: delete country = " + check);

            return Path.COMMAND_LIST_COUNTRY;

        } catch (Exception ex) {
            return Path.ERROR_PAGE;
        }
    }
}
