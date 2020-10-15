package org.bohdan.web.command.manager;

import org.apache.log4j.Logger;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;
import org.bohdan.web.command.admin.AccountAdmin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

public class AccountManager extends Command {

    private final static Logger logger = Logger.getLogger(AccountManager.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String defLocale = (String) Config.get(session, "javax.servlet.jsp.jstl.fmt.locale");
        logger.trace("LOG: defLocale = " + defLocale);

        session.setAttribute("localeDef", defLocale);

        return Path.ACCOUNT_MANAGER;
    }
}
