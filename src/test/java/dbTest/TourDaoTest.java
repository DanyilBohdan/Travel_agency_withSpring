package dbTest;

import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.DBManager;
import org.bohdan.model.general.TourView;
import org.bohdan.model.Tour;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class TourDaoTest {

    private ConnectionFactory connectionFactory;
    private TourDao tourDao;
    private int start;
    private int total;

    @Before
    public void setUp() throws NamingException {
        connectionFactory = DBManager.getInstance();
        tourDao = new TourDao(connectionFactory);
        tourDao.setFilter(0);
        start = 1;
        total = tourDao.findCountTours();
    }

    private Tour createTour() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Tour tour = Tour.createTour("Tour Test", "Тур Тест", "descriptionTest", "описание", 1300, 6,
                4, new Date(sdf.parse("2020-12-24").getTime()), 9, 0, new TypeTourDao(connectionFactory).findAll().get(0).getId(),
                new CountryDao(connectionFactory).findAll().get(0).getId());

        boolean check = tourDao.create(tour);

        Assert.assertTrue(check);

        return tour;
    }

    private void deleteTour(Integer id) throws ParseException {
        boolean check = tourDao.delete(id);

        Assert.assertTrue(check);
    }

    @Test
    public void findByLocaleTest() {
        List<TourView> listToursExpectedEN = tourDao.findAllByLocale("EN", start, total);
        List<TourView> listToursExpectedRU = tourDao.findAllByLocale("RU", start, total);
        for (int i = 0; i < listToursExpectedEN.size(); i++) {
            Assert.assertEquals(listToursExpectedEN.get(i).getId(), listToursExpectedRU.get(i).getId());
        }
    }

    @Test
    public void findAllToursTest() {
        List<Tour> listToursExpected = tourDao.findAllTour();

        Assert.assertEquals(listToursExpected.get(0).getId(), tourDao.findAllTour().get(0).getId());
    }

    @Test
    public void findByIdLocaleTest() throws ParseException {

        Tour tour = createTour();
        List<Tour> listToursExpected = tourDao.findAllTour();
        Tour tourExpected = listToursExpected.get(listToursExpected.size() - 1);
        Assert.assertEquals(tourExpected.getId(), tour.getId());
        deleteTour(tour.getId());
    }

    @Test
    public void tourUpdateAndSearchNameTest() throws ParseException {

        Tour tour = createTour();

        Tour tourUpdate = Tour.createTour("TourUpdate Test", "Тур Тест", "descriptionUpdateTest", "описание", 900, 3,
                3, new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-15").getTime()), 5, 0, new TypeTourDao(connectionFactory).findAll().get(0).getId(),
                new CountryDao(connectionFactory).findAll().get(0).getId());
        List<Tour> listToursExpected = tourDao.findAllTour();
        tourUpdate.setId(listToursExpected.get(listToursExpected.size() - 1).getId());
        tourDao.update(tourUpdate);
        Assert.assertEquals(tour.getId(), tourUpdate.getId());
        Assert.assertEquals(tour.getId(), tourDao.searchEntity("EN", "TourUpdate Test", start, total).get(0).getId());
        deleteTour(tourUpdate.getId());
    }

    @Test
    public void findAllByTypeLocaleTest() throws ParseException {

        Tour tour = createTour();

        List<TourView> listToursExpected = tourDao.findAllByTypeLocale("EN", new TypeTourDao(connectionFactory).findAll().get(0).getNameEn(), start, total);
        TourView tourExpected = listToursExpected.get(listToursExpected.size() - 1);
        Assert.assertEquals(tour.getId(), tourExpected.getId());

        deleteTour(tourExpected.getId());
    }

    @Test
    public void findAllByCountryLocaleTest() throws ParseException {

        Tour tour = createTour();

        List<TourView> listToursExpected = tourDao.findAllByCountryLocale("EN", new CountryDao(connectionFactory).findAll().get(0).getNameEn(), start, total);
        TourView tourExpected = listToursExpected.get(listToursExpected.size() - 1);
        Assert.assertEquals(tour.getId(), tourExpected.getId());

        deleteTour(tourExpected.getId());
    }

    @Test
    public void findAllByRangePriceLocaleTest() throws ParseException {

        Tour tour = createTour();

        String range = "price";

        List<TourView> listToursExpected = tourDao.searchByRange(range, "EN", "1290", "1310", start, total);
        TourView tourExpected = listToursExpected.get(listToursExpected.size() - 1);
        Assert.assertEquals(tour.getId(), tourExpected.getId());

        deleteTour(tourExpected.getId());
    }

    @Test
    public void findAllByRangeCountPeopleLocaleTest() throws ParseException {

        Tour tour = createTour();

        String range = "count_people";

        List<TourView> listToursExpected = tourDao.searchByRange(range, "EN", "5", "7", start, total);
        TourView tourExpected = listToursExpected.get(listToursExpected.size() - 1);
        Assert.assertEquals(tour.getId(), tourExpected.getId());

        deleteTour(tourExpected.getId());
    }

    @Test
    public void findAllByRangeMarkHotelLocaleTest() throws ParseException {

        Tour tour = createTour();

        String range = "mark_hotel";

        List<TourView> listToursExpected = tourDao.searchByRange(range, "EN", "3", "5", start, total);
        TourView tourExpected = listToursExpected.get(listToursExpected.size() - 1);
        Assert.assertEquals(tour.getId(), tourExpected.getId());

        deleteTour(tourExpected.getId());
    }

    @Test
    public void updateDiscountTest() throws ParseException {

        Tour tour = createTour();

        float updateDiscount = 0.3f;

        boolean check = tourDao.updateDiscount(updateDiscount, tour.getId());
        Assert.assertTrue(check);
        List<Tour> listToursExpected = tourDao.findAllTour();
        Tour tourExpected = listToursExpected.get(listToursExpected.size() - 1);
        Assert.assertEquals(tourDao.findIDTour(tour.getId()).getDiscount(), tourExpected.getDiscount(), 0.0);

        deleteTour(tourExpected.getId());
    }
}
