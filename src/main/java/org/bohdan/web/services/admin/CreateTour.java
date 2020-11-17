package org.bohdan.web.services.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.model.Country;
import org.bohdan.model.Tour;
import org.bohdan.model.TypeTour;
import org.bohdan.web.Path;
import org.bohdan.web.Validation;
import org.bohdan.web.services.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Create tour
 *
 * @author Bohdan Daniel
 */
public class CreateTour extends Command {

    private static final Logger logger = Logger.getLogger(CreateTour.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            Tour tourView = new Tour();
            String nameEN = request.getParameter("nameEN");
            String nameRU = request.getParameter("nameRU");
            tourView.setNameEn(nameEN);
            tourView.setNameRu(nameRU);
            logger.debug("Log: name : " + nameEN + ", " + nameRU);

            String typeEN = request.getParameter("typeEN");
            String typeRU = request.getParameter("typeRU");
            logger.debug("Log: type : " + typeEN + ", " + typeRU);
            request.setAttribute("typeDef", TypeTour.create(typeEN, typeRU));

            String countryEN = request.getParameter("countryEN");
            String countryRU = request.getParameter("countryRU");
            logger.debug("Log: country : " + countryEN + ", " + countryRU);
            request.setAttribute("countryDef", Country.create(countryEN, countryRU));

            float price = Float.parseFloat(request.getParameter("price"));
            logger.debug("Log: price : " + price);
            tourView.setPrice(price);

            String descriptionEN = request.getParameter("descriptionEN");
            String descriptionRU = request.getParameter("descriptionRU");
            logger.debug("Log: description : " + descriptionEN + ", " + descriptionRU);
            tourView.setDescEn(descriptionEN);
            tourView.setDescRu(descriptionRU);

            int count_people = Integer.parseInt(request.getParameter("count_people"));
            logger.debug("Log: count_people : " + count_people);
            tourView.setCountPeople(count_people);

            int mark_hotel = Integer.parseInt(request.getParameter("mark_hotel"));
            logger.debug("Log: mark_hotel : " + mark_hotel);
            tourView.setMarkHotel(mark_hotel);

            Date start_date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("start_date")).getTime());
            logger.debug("Log: start_date : " + start_date);
            tourView.setStartDate(start_date);

            int days = Integer.parseInt(request.getParameter("days"));
            logger.debug("Log: days : " + days);
            tourView.setDays(days);

            request.setAttribute("tour", tourView);

            String checkVal = Validation.validateTour(nameEN, nameRU, typeEN, typeRU, countryEN, countryRU, descriptionEN, descriptionRU,
                    price, count_people, mark_hotel, start_date, days, 0);
            if (!checkVal.equals("null")) {
                request.setAttribute("errorVal", checkVal);
                logger.error("errorMessage --> " + checkVal);
                return Path.CREATE_TOUR;
            }

            boolean check = new TourDao(connectionPool).create(Tour.createTour(nameEN, nameRU, descriptionEN, descriptionRU, price, count_people,
                    mark_hotel, start_date, days, 0, new TypeTourDao(connectionPool).findByName(typeEN).getId(),
                    new CountryDao(connectionPool).findByName(countryEN).getId()));
            logger.info("Log: create Tour  check : " + check);

            return Path.COMMAND_TOURS_ADMIN;

        } catch (Exception ex) {
            logger.error("Log: " + ex);
            request.setAttribute("errorMessage", "Incorrect data entered");
            logger.error("errorMessage --> " + "Incorrect data entered");
            return Path.ERROR_PAGE;
        }
    }
}
