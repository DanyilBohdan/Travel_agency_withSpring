package org.bohdan.web.controllers;

import org.bohdan.model.User;
import org.bohdan.model.general.OrderTours;
import org.bohdan.model.general.TourView;
import org.bohdan.web.Path;
import org.bohdan.web.services.OrderService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrderControllerTest {

    private final OrderService orderService = mock(OrderService.class);

    private final HttpSession session = mock(HttpSession.class);

    private final HttpServletRequest request = mock(HttpServletRequest.class);

    private final OrderController orderController = new OrderController(orderService, session);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

    @Test
    public void getViewOrdersTest() throws Exception {

        Date d = new Date();
        Object date = new java.sql.Timestamp(d.getTime());

        OrderTours first = OrderTours.createOrderTour(1, 1, "Test1", "testType1", "testCountry1",
                "paid", "test1@gmail.com", 555, 4, 5, date, 1, 2, date);

        OrderTours second = OrderTours.createOrderTour(2, 2, "Test2", "testType2", "testCountry2",
                "registered", "test2@gmail.com", 555, 3, 3, date, 2, 3, date);

        when(session.getAttribute("localeDef")).thenReturn("EN");
        when(this.orderService.viewOrders(session))
                .thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/order/view"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/listOrdersAdmin"))
                .andExpect(forwardedUrl("admin/listOrdersAdmin"))
                .andExpect(model().attribute("orders", hasSize(2)))
                .andExpect(model().attribute("orders", hasItem(
                        allOf(
                                hasProperty("orderId", is(first.getOrderId())),
                                hasProperty("name", is(first.getName())),
                                hasProperty("status", is(first.getStatus()))
                        )
                )))
                .andExpect(model().attribute("orders", hasItem(
                        allOf(
                                hasProperty("tourId", is(second.getTourId())),
                                hasProperty("name", is(second.getName()))
                        )
                )));
    }

    @Test
    public void searchByStatusOrderTest() throws Exception {

        String search = "paid";

        Date d = new Date();
        Object date = new java.sql.Timestamp(d.getTime());

        OrderTours first = OrderTours.createOrderTour(1, 1, "Test1", "testType1", "testCountry1",
                "paid", "test1@gmail.com", 555, 4, 5, date, 1, 2, date);

        OrderTours second = OrderTours.createOrderTour(2, 2, "Test2", "testType2", "testCountry2",
                "registered", "test2@gmail.com", 555, 3, 3, date, 2, 3, date);

        when(session.getAttribute("defLocale")).thenReturn("EN");
        when(request.getParameter("searchStatus")).thenReturn(search);
        when(this.orderService.searchByStatusOrder("EN", search))
                .thenReturn(Collections.singletonList(first));

        mockMvc.perform(get("/order/view/searchByStatus")
                .param("searchStatus", search))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/listOrdersAdmin"))
                .andExpect(forwardedUrl("admin/listOrdersAdmin"))
                .andExpect(model().attribute("selectDef", is("paid")))
                .andExpect(model().attribute("orders", hasSize(1)))
                .andExpect(model().attribute("orders", hasItem(
                        allOf(
                                hasProperty("status", is(search))
                        )
                )));
    }

    @Test
    public void updateStatusOrderTest() throws Exception {

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("selectStatus")).thenReturn("paid");

        when(this.orderService.updateStatusOrder(1, "paid"))
                .thenReturn(true);

        mockMvc.perform(post("/order/view/update/status")
                .param("id", "1")
                .param("selectStatus", "paid"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/order/view"))
                .andExpect(redirectedUrl("/order/view"))
                .andExpect(model().attribute("orders", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("status", is("paid"))
                         )
                )));
    }

    @Test
    public void registerTourOrderTest() throws Exception {

        Date d = new Date();

        TourView tour = TourView.createTour("testName1", "testType1", "testCountry1",
                "testDesc1", 980, 4, 5, d, 4, 5);
        tour.setId(1);

        User user = User.createUser("testUser", "1111", "test@gmail.com", "(123) 12345612", true, 1);
        user.setId(1);

        String lang = "EN";
        String id = "1";

        when(session.getAttribute("defLocale")).thenReturn(lang);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("id")).thenReturn(id);
        when(this.orderService.registerView(lang, Integer.parseInt(id), user))
                .thenReturn(tour);

        mockMvc.perform(get("/order/register/view")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(Path.REGISTER_TOUR))
                .andExpect(forwardedUrl(Path.REGISTER_TOUR))
                .andExpect(model().attribute("tour", hasProperty("id",is(1))))
                .andExpect(model().attribute("tour", hasProperty("name",is("testName1"))));

    }

}
