package dbTest;

import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.DBManager;
import org.bohdan.db.bean.ListBean;
import org.bohdan.db.bean.TourView;
import org.bohdan.db.entity.Tour;
import org.bohdan.db.entity.TypeTour;
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
        TourDao.setFilter(0);
        tourDao = new TourDao(connectionFactory);
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

}
