package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.entity.Country;
import org.bohdan.db.entity.Tour;
import org.bohdan.db.entity.TypeTour;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet("/edit")
public class EditTour extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EditTour.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Tour tour = new TourDao().findIDTour(id);
            TypeTour typeTour = new TypeTourDao().findEntityById(tour.getType_tour_id());
            Country country = new CountryDao().findEntityById(tour.getCountry_id());
            if (tour != null || typeTour != null || country != null) {
                req.setAttribute("tour", tour);
                req.setAttribute("typeTourOut", new TypeTourDao().findAll());
                req.setAttribute("typeDef", typeTour);
                req.setAttribute("countryOut", new CountryDao().findAll());
                req.setAttribute("countryDef", country);
                getServletContext().getRequestDispatcher(Path.EDIT_TOUR).forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher(Path.ERROR_PAGE).forward(req, resp);
            }
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher(Path.ERROR_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nameEN = req.getParameter("nameEN");
            String nameRU = req.getParameter("nameRU");
            logger.info("Log: name : " + nameEN +", " + nameRU);
            String typeEN = req.getParameter("typeEN");
            String typeRU = req.getParameter("typeRU");
            logger.info("Log: type : " + typeEN +", " + typeRU);
            String countryEN = req.getParameter("countryEN");
            String countryRU = req.getParameter("countryRU");
            logger.info("Log: country : " + countryEN +", " + countryRU);
            float price = Float.parseFloat(req.getParameter("price"));
            logger.info("Log: price : " + price);
            String descriptionEN = req.getParameter("descriptionEN");
            String descriptionRU = req.getParameter("descriptionRU");
            logger.info("Log: description : " + descriptionEN +", " + descriptionRU);
            int count_people = Integer.parseInt(req.getParameter("count_people"));
            logger.info("Log: count_people : " + count_people);
            int mark_hotel = Integer.parseInt(req.getParameter("mark_hotel"));
            logger.info("Log: mark_hotel : " + mark_hotel);
            Date start_date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("start_date")).getTime());
            logger.info("Log: start_date : " + start_date);
            //Date start_date = Date.valueOf("2020-4-5");
            int days = Integer.parseInt(req.getParameter("days"));
            logger.info("Log: days : " + days);
            float discount = Float.parseFloat(req.getParameter("discount"));
            logger.info("Log: discount : " + discount);

            int id = Integer.parseInt(req.getParameter("id"));

            Tour tour = Tour.createTour(nameEN, nameRU, descriptionEN, descriptionRU, price, count_people,
                    mark_hotel, start_date, days, discount, new TypeTourDao().findByName(typeEN).getId(), new CountryDao().findByName(countryEN).getId());

            tour.setId(id);
            boolean check = new TourDao().update(tour);
            logger.info("Log: update Tour  check : " + check);

            resp.sendRedirect(req.getContextPath() + Path.MAIN);

        } catch (Exception ex) {
            logger.error("Log: " + ex);
            getServletContext().getRequestDispatcher(Path.EDIT_TOUR).forward(req, resp);
        }
    }
}
