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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * View edit tour page
 *
 * @author Bohdan Daniel
 */

public class TourCommand {

    private static final Logger logger = Logger.getLogger(TourCommand.class);

    public ModelAndView editTourView(HttpServletRequest request, ModelAndView modelAndView, TourDao tourDao,
                                     TypeTourDao typeTourDao, CountryDao countryDao) {

        int id = Integer.parseInt(request.getParameter("id"));
        Tour tourOld = tourDao.findIDTour(id);
        if (tourOld == null) {
            return new ModelAndView(Path.ERROR_PAGE);
        }

        modelAndView.addObject("tour", tourOld);

        TypeTour typeTour = typeTourDao.findEntityById(tourOld.getTypeTourId());
        Country country = countryDao.findEntityById(tourOld.getCountryId());
        if (typeTour == null || country == null) {
            return new ModelAndView(Path.ERROR_PAGE);
        }

        HttpSession session = request.getSession();

        session.setAttribute("typeTourOut", typeTourDao.findAll());
        modelAndView.addObject("typeDef", typeTour);
        logger.debug("Log: typeDef --> " + typeTour);

        session.setAttribute("countryOut", countryDao.findAll());
        modelAndView.addObject("countryDef", country);
        logger.debug("Log: country --> " + country);

        return modelAndView;
    }

    public ModelAndView createTourView(HttpServletRequest request, ModelAndView modelAndView,
                                 TypeTourDao typeTourDao, CountryDao countryDao) {

        List<TypeTour> typeTours = typeTourDao.findAll();
        List<Country> countries = countryDao.findAll();
        if (typeTours == null || countries == null) {
            return new ModelAndView(Path.ERROR_PAGE);
        }

        HttpSession session = request.getSession();

        session.setAttribute("typeTourOut", typeTourDao.findAll());
        modelAndView.addObject("typeDef", typeTours.get(0));
        logger.debug("Log: typeDef --> " + typeTours.get(0));

        session.setAttribute("countryOut", countryDao.findAll());
        modelAndView.addObject("countryDef", countries.get(0));
        logger.debug("Log: country --> " + countries.get(0));

        return modelAndView;
    }

    public ModelAndView createTour(HttpServletRequest request, ModelAndView modelAndView, TourDao tourDao,
                                   TypeTourDao typeTourDao, CountryDao countryDao){
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
            modelAndView.addObject("typeDef", TypeTour.create(typeEN, typeRU));

            String countryEN = request.getParameter("countryEN");
            String countryRU = request.getParameter("countryRU");
            logger.debug("Log: country : " + countryEN + ", " + countryRU);
            modelAndView.addObject("countryDef", Country.create(countryEN, countryRU));

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

            Date start_date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("start_date")).getTime());
            logger.debug("Log: start_date : " + start_date);
            tourView.setStartDate(start_date);

            int days = Integer.parseInt(request.getParameter("days"));
            logger.debug("Log: days : " + days);
            tourView.setDays(days);

            modelAndView.addObject("tour", tourView);

            String checkVal = Validation.validateTour(nameEN, nameRU, typeEN, typeRU, countryEN, countryRU, descriptionEN, descriptionRU,
                    price, count_people, mark_hotel, start_date, days, 0);
            if (!checkVal.equals("null")) {
                modelAndView.addObject("errorVal", checkVal);
                logger.error("errorMessage --> " + checkVal);
                return new ModelAndView(Path.ERROR_PAGE);
            }

            boolean check = tourDao.create(Tour.createTour(nameEN, nameRU, descriptionEN, descriptionRU, price, count_people,
                    mark_hotel, start_date, days, 0, typeTourDao.findByName(typeEN).getId(),
                    countryDao.findByName(countryEN).getId()));
            logger.info("Log: create Tour  check : " + check);

            return modelAndView;

        } catch (Exception ex) {
            logger.error("Log: " + ex);
            modelAndView.addObject("errorMessage", "Incorrect data entered");
            logger.error("errorMessage --> " + "Incorrect data entered");
            return new ModelAndView(Path.ERROR_PAGE);
        }
    }

    public ModelAndView editTour(HttpServletRequest request, ModelAndView modelAndView, TourDao tourDao,
                                 TypeTourDao typeTourDao, CountryDao countryDao) {
        try {
            Tour tourView = new Tour();
            int id = Integer.parseInt(request.getParameter("id"));
            logger.debug("Log: id --> " + id);
            tourView.setId(id);
            String nameEN = request.getParameter("nameEN");
            String nameRU = request.getParameter("nameRU");
            tourView.setNameEn(nameEN);
            tourView.setNameRu(nameRU);
            logger.debug("Log: name : " + nameEN + ", " + nameRU);

            String typeEN = request.getParameter("typeEN");
            String typeRU = request.getParameter("typeRU");
            logger.debug("Log: type : " + typeEN + ", " + typeRU);
            modelAndView.addObject("typeDef", TypeTour.create(typeEN, typeRU));

            String countryEN = request.getParameter("countryEN");
            String countryRU = request.getParameter("countryRU");
            logger.debug("Log: country : " + countryEN + ", " + countryRU);
            modelAndView.addObject("countryDef", Country.create(countryEN, countryRU));

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

            Date start_date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("start_date")).getTime());
            logger.debug("Log: start_date : " + start_date);
            tourView.setStartDate(start_date);

            int days = Integer.parseInt(request.getParameter("days"));
            logger.debug("Log: days : " + days);
            tourView.setDays(days);

            modelAndView.addObject("tour", tourView);

            String checkVal = Validation.validateTour(nameEN, nameRU, typeEN, typeRU, countryEN, countryRU, descriptionEN, descriptionRU,
                    price, count_people, mark_hotel, start_date, days, 0);

            if (!checkVal.equals("null")) {
                modelAndView.addObject("errorVal", checkVal);
                logger.error("errorMessage --> " + checkVal);
                return modelAndView;
            }

            Tour tour = Tour.createTour(nameEN, nameRU, descriptionEN, descriptionRU, price, count_people,
                    mark_hotel, start_date, days, 0, typeTourDao.findByName(typeEN).getId(),
                    countryDao.findByName(countryEN).getId());

            tour.setId(id);

            logger.info("Log: tour : " + tour);

            boolean check = tourDao.update(tour);
            logger.info("Log: update Tour  check : " + check);

            return new ModelAndView(Path.COMMAND_TOURS_ADMIN);

        } catch (Exception ex) {
            logger.error("Log: " + ex);
            modelAndView.addObject("errorVal", "Incorrect data entered");
            logger.error("errorMessage --> " + "Incorrect data entered");
            return modelAndView;
        }
    }
}
