package org.bohdan.web.command.admin;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.entity.*;
import org.bohdan.web.Path;
import org.bohdan.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class CreateTour extends Command {

    private static final Logger logger = Logger.getLogger(CreateTour.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            String nameEN = request.getParameter("nameEN");
            String nameRU = request.getParameter("nameRU");
            logger.info("Log: name : " + nameEN + ", " + nameRU);
            String typeEN = request.getParameter("typeEN");
            String typeRU = request.getParameter("typeRU");
            logger.info("Log: type : " + typeEN + ", " + typeRU);
            String countryEN = request.getParameter("countryEN");
            String countryRU = request.getParameter("countryRU");
            logger.info("Log: country : " + countryEN + ", " + countryRU);
            float price = Float.parseFloat(request.getParameter("price"));
            logger.info("Log: price : " + price);
            String descriptionEN = request.getParameter("descriptionEN");
            String descriptionRU = request.getParameter("descriptionRU");
            logger.info("Log: description : " + descriptionEN + ", " + descriptionRU);
            int count_people = Integer.parseInt(request.getParameter("count_people"));
            logger.info("Log: count_people : " + count_people);
            int mark_hotel = Integer.parseInt(request.getParameter("mark_hotel"));
            logger.info("Log: mark_hotel : " + mark_hotel);
            Date start_date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("start_date")).getTime());
            logger.info("Log: start_date : " + start_date);
            int days = Integer.parseInt(request.getParameter("days"));
            logger.info("Log: days : " + days);
            float discount = Float.parseFloat(request.getParameter("discount"));
            logger.info("Log: discount : " + discount);

            price = TourDao.changePrice(price, discount);

            boolean check = new TourDao().create(Tour.createTour(nameEN, nameRU, descriptionEN, descriptionRU, price, count_people,
                    mark_hotel, start_date, days, discount, new TypeTourDao().findByName(typeEN).getId(), new CountryDao().findByName(countryEN).getId()));
            logger.info("Log: create Tour  check : " + check);

            return Path.COMMAND_TOURS_ADMIN;

        } catch (Exception ex) {
            logger.error("Log: " + ex);
            return Path.COMMAND_CREATE_TOUR_ADMIN;
        }
    }
}
