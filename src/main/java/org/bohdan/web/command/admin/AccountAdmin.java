package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * View account admin
 *
 * @author Bohdan Daniel
 */
public class AccountAdmin extends Command {

    private final static Logger logger = Logger.getLogger(AccountAdmin.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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

        request.setAttribute("commandPage", "accountAdmin");

        return Path.ACCOUNT_ADMIN;
    }
}
