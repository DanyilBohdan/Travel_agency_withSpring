package dbTest;

import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.OrderDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.DBManager;
import org.bohdan.db.bean.OrderTours;
import org.bohdan.db.bean.OrderToursByIdUser;
import org.bohdan.db.entity.Order;
import org.bohdan.db.entity.Tour;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderDaoTest {

    private ConnectionFactory connectionFactory;
    private OrderDao orderDao;

    @Before
    public void setUp() throws NamingException {
        connectionFactory = DBManager.getInstance();
        orderDao = new OrderDao(connectionFactory);
    }

    private Order createOrder() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Tour tour = Tour.createTour("Tour Test", "Тур Тест", "descriptionTest", "описание", 1300, 6,
                4, new Date(sdf.parse("2020-12-24").getTime()), 9, 0, new TypeTourDao(connectionFactory).findAll().get(0).getId(),
                new CountryDao(connectionFactory).findAll().get(0).getId());

        boolean checkTour = new TourDao(connectionFactory).create(tour);

        java.util.Date d = new java.util.Date();
        Object date = new java.sql.Timestamp(d.getTime());

        Order order = Order.createOrderTour("registered", date, tour.getId(), 1);

        boolean check = orderDao.create(order);
        Assert.assertTrue(check);

        return order;
    }

    private void deleteTour(Integer id) {
        boolean checkTour = new TourDao(connectionFactory).delete(id);

        Assert.assertTrue(checkTour);
    }

    @Test
    public void findAllOrdersTest() {
        List<Order> listOrdersExpected = orderDao.findAll();

        Assert.assertEquals(listOrdersExpected.get(0).getId(), orderDao.findAll().get(0).getId());
    }

    @Test
    public void createAndDeleteTest() throws ParseException {
        Order order = createOrder();
        List<Order> listOrdersExpected = orderDao.findAll();
        Order orderExpected = listOrdersExpected.get(listOrdersExpected.size() - 1);
        Assert.assertEquals(orderExpected.getId(), order.getId());
        deleteTour(order.getTourId());
    }

    @Test
    public void findToursByIdUserTest() throws ParseException {
        Order order = createOrder();
        List<OrderToursByIdUser> listTours = orderDao.findToursByIdUser(order.getUserId());
        Order orderExpected = orderDao.findEntityById(listTours.get(listTours.size() -1).getOrderId());
        Assert.assertEquals(orderExpected.getUserId(), order.getUserId());
        deleteTour(order.getTourId());
    }

    @Test
    public void findAllOrdersByLocaleTest() {
        List<OrderTours> listOrdersExpectedEN = orderDao.findAllOrdersLocale("EN");
        List<OrderTours> listOrdersExpectedRU = orderDao.findAllOrdersLocale("RU");
        for (int i = 0; i < listOrdersExpectedEN.size(); i++) {
            Assert.assertEquals(listOrdersExpectedEN.get(i).getOrderId(), listOrdersExpectedRU.get(i).getOrderId());
        }
    }

    @Test
    public void orderStatusUpdateTest() throws ParseException {

        Order order = createOrder();

        boolean check = orderDao.updateStatus("paid", order.getId());
        Assert.assertTrue(check);
        deleteTour(order.getTourId());
    }
}
