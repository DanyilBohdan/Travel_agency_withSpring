package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

public class SettingCommand{

    private static final Logger logger = Logger.getLogger(SettingCommand.class);

    public static void change(HttpServletRequest request) throws IOException, ServletException {

        String localeToSet = request.getParameter("localeToSet");
        if (localeToSet != null && !localeToSet.isEmpty()) {
            HttpSession session = request.getSession();
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
            session.setAttribute("defaultLocale", localeToSet);
        }
        logger.trace("Set the session attribute: defaultLocaleName --> " + localeToSet);

        logger.info("Locale for user: defaultLocale --> " + localeToSet);
    }
}
