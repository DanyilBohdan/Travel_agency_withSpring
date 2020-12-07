package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * View account admin
 *
 * @author Bohdan Daniel
 */

public class AccountAdminAndManagerCommand {

    private final static Logger logger = Logger.getLogger(AccountAdminAndManagerCommand.class);

    public ModelAndView execute(HttpServletRequest request, ModelAndView modelAndView) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");
        logger.info("LOG: localeParam = " + lang);

        if (lang != null && !lang.isEmpty()) {
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
            session.setAttribute("defLocale", lang);
        } else {
            lang = (String) session.getAttribute("defLocale");
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", lang);
        }
        logger.info("LOG: localeFinal = " + lang);


        modelAndView.addObject("commandPage", request.getRequestURI());

        logger.info("Command finished");
        return modelAndView;
    }
}
