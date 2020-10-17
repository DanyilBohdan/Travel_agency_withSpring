package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.entity.Country;
import org.bohdan.db.entity.Tour;
import org.bohdan.db.entity.TypeTour;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class EditTour extends Command {

    private static final Logger logger = Logger.getLogger(EditTour.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String nameEN = req.getParameter("nameEN");
            String nameRU = req.getParameter("nameRU");
            logger.debug("Log: name : " + nameEN + ", " + nameRU);

            String typeEN = req.getParameter("typeEN");
            String typeRU = req.getParameter("typeRU");
            logger.debug("Log: type : " + typeEN + ", " + typeRU);
            String countryEN = req.getParameter("countryEN");
            String countryRU = req.getParameter("countryRU");
            logger.debug("Log: country : " + countryEN + ", " + countryRU);
            float price = Float.parseFloat(req.getParameter("price"));
            logger.debug("Log: price : " + price);
            String descriptionEN = req.getParameter("descriptionEN");
            String descriptionRU = req.getParameter("descriptionRU");
            logger.debug("Log: description : " + descriptionEN + ", " + descriptionRU);
            int count_people = Integer.parseInt(req.getParameter("count_people"));
            logger.debug("Log: count_people : " + count_people);
            int mark_hotel = Integer.parseInt(req.getParameter("mark_hotel"));
            logger.debug("Log: mark_hotel : " + mark_hotel);
            Date start_date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("start_date")).getTime());
            logger.debug("Log: start_date : " + start_date);
            int days = Integer.parseInt(req.getParameter("days"));
            logger.debug("Log: days : " + days);
            float discount = Float.parseFloat(req.getParameter("discount"));
            logger.debug("Log: discount : " + discount);

            String checkVal = Validation.validateTour(nameEN, nameRU, typeEN, typeRU, countryEN, countryRU, descriptionEN, descriptionRU,
                    price, count_people, mark_hotel, start_date, days, discount);
            if (!checkVal.equals("null")) {
                req.setAttribute("errorMessage", checkVal);
                logger.error("errorMessage --> " + checkVal);
                return Path.ERROR_PAGE;
            }

            price = TourDao.changePrice(price, discount);

            Tour tour = Tour.createTour(nameEN, nameRU, descriptionEN, descriptionRU, price, count_people,
                    mark_hotel, start_date, days, discount, new TypeTourDao().findByName(typeEN).getId(), new CountryDao().findByName(countryEN).getId());

            logger.info("Log: tour : " + tour);
            tour.setId(id);
            boolean check = new TourDao().update(tour);
            logger.info("Log: update Tour  check : " + check);

            return Path.COMMAND_TOURS_ADMIN;

        } catch (Exception ex) {
            logger.error("Log: " + ex);
            return Path.EDIT_TOUR;
        }
    }
}
