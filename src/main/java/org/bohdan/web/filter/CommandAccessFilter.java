package org.bohdan.web.filter;


import org.apache.log4j.Logger;
import org.bohdan.db.entity.Role;
import org.bohdan.web.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class CommandAccessFilter implements Filter {

    private static final Logger logger = Logger.getLogger(CommandAccessFilter.class);

    private static Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
    private static List<String> commons = new ArrayList<String>();
    private static List<String> outOfControl = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        logger.debug("Filter initialization starts");

        accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter("admin")));
        accessMap.put(Role.MANAGER, asList(filterConfig.getInitParameter("manager")));
        accessMap.put(Role.USER, asList(filterConfig.getInitParameter("user")));
        logger.trace("Access map --> " + accessMap);

        commons = asList(filterConfig.getInitParameter("common"));
        logger.trace("Common commands --> " + commons);

        // out of control
        outOfControl = asList(filterConfig.getInitParameter("out-of-control"));
        logger.trace("Out of control commands --> " + outOfControl);

        logger.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Filter starts");

        if (accessAllowed(servletRequest)) {
            logger.debug("Filter finished");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";

            servletRequest.setAttribute("errorMessage", errorMessage);
            logger.trace("Set the request attribute: errorMessage --> " + errorMessage);

            servletRequest.getRequestDispatcher(Path.ERROR_PAGE)
                    .forward(servletRequest, servletResponse);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            logger.error("log: commandName = " + commandName);
            return false;
        }

        if (outOfControl.contains(commandName))
            return true;

        HttpSession session = httpRequest.getSession(false);
        if (session == null)
            return false;

        Role userRole = (Role) session.getAttribute("userRole");
        if (userRole == null)
            return false;

        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);
    }

    @Override
    public void destroy() {

        logger.debug("Filter destruction starts");
        // do nothing
        logger.debug("Filter destruction finished");
    }

    /**
     * Extracts parameter values from string.
     *
     * @param str parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
