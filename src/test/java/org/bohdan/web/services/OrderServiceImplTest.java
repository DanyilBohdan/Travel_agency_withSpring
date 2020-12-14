package org.bohdan.web.services;

import org.bohdan.db.DAO.OrderDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.model.general.OrderTours;
import org.bohdan.model.general.TourView;
import org.bohdan.web.services.impl.OrderServiceImpl;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    private final HttpSession session = mock(HttpSession.class);

    private final OrderDao orderDao = mock(OrderDao.class);

    private final TourDao tourDao = mock(TourDao.class);

    private final OrderService orderService = new OrderServiceImpl(orderDao, tourDao);

    @Test
    public void viewOrdersTest() throws Exception {
        List<OrderTours> orders = generateOrderTest(3);
        String lang = "EN";
        doReturn(orders).when(orderDao).findAllOrdersLocale(lang);

        when(session.getAttribute("localeDef")).thenReturn("EN");
        List<OrderTours> orderTours = orderService.viewOrders(session);

        verify(orderDao).findAllOrdersLocale(lang);
        assertThat(orderTours, hasSize(3));

        orders.forEach(
                order -> assertThat(orderTours, hasItem(
                        allOf(
                                hasProperty("orderId", is(order.getOrderId())),
                                hasProperty("tourId", is(order.getTourId())),
                                hasProperty("name", is(order.getName())),
                                hasProperty("type", is(order.getType())),
                                hasProperty("country", is(order.getCountry())),
                                hasProperty("price", is(order.getPrice())),
                                hasProperty("countPeople", is(order.getCountPeople())),
                                hasProperty("markHotel", is(order.getMarkHotel())),
                                hasProperty("startDate", is(order.getStartDate())),
                                hasProperty("days", is(order.getDays())),
                                hasProperty("discount", is(order.getDiscount())),
                                hasProperty("status", is(order.getStatus())),
                                hasProperty("dateReg", is(order.getDateReg())),
                                hasProperty("login", is(order.getLogin()))
                        )))
        );
    }

    private List<OrderTours> generateOrderTest(int count) throws ParseException {
        List<OrderTours> orders = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse("01/01/2021");
        for (int i = 0; i < count; i++) {
            OrderTours order = OrderTours.createOrderTour(i, i, "Test" + i, "testType" + i,
                    "testCountry" + i, "paid", "test" + i + "@gmail.com", 555,
                    4, 5, date, 1, 2, date);
            orders.add(order);
        }
        return orders;
    }
}
