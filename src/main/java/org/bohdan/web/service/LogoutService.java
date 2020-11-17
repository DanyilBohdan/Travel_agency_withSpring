package org.bohdan.web.service;

import org.apache.log4j.Logger;
import org.bohdan.web.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * LogOut command
 *
 * @author Bohdan Daniel
 */
@Service
public class LogoutService {

    private final static Logger logger = Logger.getLogger(LogoutService.class);

    @Autowired
    public LogoutService() {
    }

    public ModelAndView execute(HttpServletRequest request, String nameView) throws IOException, ServletException {
        logger.debug("Command starts");

        HttpSession sessionOld = request.getSession(false);
        String lang = (String) sessionOld.getAttribute("defLocale");
        if (sessionOld != null) {
            sessionOld.invalidate();
        }

        HttpSession session = request.getSession();
        session.setAttribute("defLocale", lang);
        logger.info("log: lang ----------------> " + lang);

        Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);

        logger.debug("Command finished");
        return new ModelAndView(nameView);
    }
}
