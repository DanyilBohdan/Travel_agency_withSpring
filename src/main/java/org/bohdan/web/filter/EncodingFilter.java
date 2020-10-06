package org.bohdan.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final Logger logger = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Filter initialization starts");
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
        logger.trace("Encoding from web.xml --> " + encoding);
        logger.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        logger.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = servletRequest.getCharacterEncoding();
        if (requestEncoding == null) {
            logger.trace("Request encoding = null, set encoding --> " + encoding);
            servletRequest.setCharacterEncoding(encoding);
        }

        logger.debug("Filter finished");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.debug("Filter destruction starts");
        logger.debug("Filter destruction finished");
    }
}
