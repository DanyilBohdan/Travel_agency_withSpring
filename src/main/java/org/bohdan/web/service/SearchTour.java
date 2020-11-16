package org.bohdan.web.service;

import org.apache.log4j.Logger;
import org.bohdan.db.ConnectionPool;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.model.general.TourView;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Impl search by method tour
 *
 * @author Bohdan Daniel
 */
public class SearchTour {

    private static final Logger logger = Logger.getLogger(SearchTour.class);

    public static List<TourView> execute(HttpServletRequest request, int check) {
        try {

            ConnectionPool dataSource = ConnectionPool.getInstance();

            List<TourView> tours = null;
            TourDao tourDao = new TourDao(dataSource);

            Integer count = tourDao.findCountTours();
            int countPage = (count / 6) + 1;
            logger.info("Log: count --> " + count);
            logger.info("Log: countPage --> " + countPage);

            request.setAttribute("countPage", countPage);

            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }

            int pageId = Integer.parseInt(page);
            int total = 6;
            if (pageId == 1) {
            } else {
                pageId = pageId - 1;
                pageId = pageId * total + 1;
            }

            String lang = (String) request.getSession().getAttribute("defLocale");

            String method = request.getParameter("method");
            logger.debug("Log: method -->" + method);

            tourDao.setFilter(check);

            if (method == null || method.equals("")) {
                tours = tourDao.findAllByLocale(lang, pageId, total);
                logger.trace("Found in DB: tours --> " + tours);
                request.setAttribute("methodDef", "");
                request.getSession().setAttribute("beginDef", "");
                request.getSession().setAttribute("endDef", "");
                request.getSession().setAttribute("selectDef", "");
                request.getSession().setAttribute("typeDef", "");
                request.getSession().setAttribute("countryDef", "");
                request.getSession().setAttribute("searchName", "");
                return tours;
            }

            switch (Objects.requireNonNull(method)) {
                case "typeTour": {
                    String type = request.getParameter("searchType");
                    if (type == null) {
                        type = (String) request.getSession().getAttribute("typeDef");
                    }
                    logger.debug("Log: type -->" + type);

                    tours = tourDao.findAllByTypeLocale(lang, type, pageId, total);

                    request.getSession().setAttribute("methodDef", "typeTour");
                    request.getSession().setAttribute("typeDef", type);
                    request.getSession().setAttribute("searchName", "");
                    break;
                }
                case "countryTour": {
                    String country = request.getParameter("searchCountry");
                    if (country == null) {
                        country = (String) request.getSession().getAttribute("countryDef");
                    }
                    logger.debug("Log: country -->" + country);

                    tours = tourDao.findAllByCountryLocale(lang, country, pageId, total);

                    request.getSession().setAttribute("methodDef", "countryTour");
                    request.getSession().setAttribute("countryDef", country);
                    request.getSession().setAttribute("searchName", "");
                    break;
                }
                case "nameTour": {
                    String text = request.getParameter("searchText");
                    if (text == null) {
                        text = (String) request.getSession().getAttribute("searchName");
                    }
                    logger.debug("Log: country -->" + text);

                    tours = tourDao.searchEntity(lang, text, pageId, total);

                    request.getSession().setAttribute("methodDef", "nameTour");
                    request.getSession().setAttribute("searchName", text);
                    break;
                }
                case "rangeTour": {
                    String begin = request.getParameter("searchBegin");
                    if (begin == null) {
                        begin = (String) request.getSession().getAttribute("beginDef");
                    }
                    logger.debug("Log: begin -->" + begin);

                    String end = request.getParameter("searchEnd");
                    if (end == null) {
                        end = (String) request.getSession().getAttribute("endDef");
                    }
                    logger.debug("Log: end -->" + end);

                    String select = request.getParameter("searchSelect");
                    if (select == null) {
                        select = (String) request.getSession().getAttribute("selectDef");
                    }
                    logger.debug("Log: select -->" + select);

                    tours = tourDao.searchByRange(select, lang, begin, end, pageId, total);

                    request.getSession().setAttribute("methodDef", "rangeTour");
                    request.getSession().setAttribute("beginDef", begin);
                    request.getSession().setAttribute("endDef", end);
                    request.getSession().setAttribute("selectDef", select);
                    break;
                }
            }
            return tours;
        } catch (Exception ex) {
            logger.trace("ex --> " + ex);
            return null;
        }
    }
}
