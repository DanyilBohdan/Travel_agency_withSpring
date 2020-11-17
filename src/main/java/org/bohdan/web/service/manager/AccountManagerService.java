package org.bohdan.web.service.manager;

import org.apache.log4j.Logger;
import org.bohdan.web.Path;
import org.bohdan.web.service.Command;
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
 * View account manager
 *
 * @author Bohdan Daniel
 */
@Service
public class AccountManagerService{

    private final static Logger logger = Logger.getLogger(AccountManagerService.class);

    @Autowired
    public AccountManagerService() {
    }

    public ModelAndView execute(HttpServletRequest request, String nameView) throws IOException, ServletException {
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

        ModelAndView modelAndView = new ModelAndView(nameView);

        modelAndView.addObject("commandPage", "/manager/account");

        logger.debug("Command finished");
        return modelAndView;
    }
}
