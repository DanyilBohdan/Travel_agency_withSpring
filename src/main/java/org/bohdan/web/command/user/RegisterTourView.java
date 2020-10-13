package org.bohdan.web.command.user;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.bean.TourView;
import org.bohdan.db.entity.Tour;
import org.bohdan.db.entity.User;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterTourView extends Command {

    private final static Logger logger = Logger.getLogger(RegisterTourView.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try{
            String lang = (String) request.getSession().getAttribute("defLocale");
            if(lang == null){
                lang = "EN";
            }
            logger.debug("Log: lang -->" + lang);

            int id = Integer.parseInt(request.getParameter("id"));

            TourView tour = new TourDao().findByIdLocale(lang, id);
            request.setAttribute("tour", tour);

            return Path.REGISTER_TOUR;

        } catch (Exception ex){
            logger.error("Log: ex --> " + ex);
            return Path.ERROR_PAGE;
        }
    }
}
