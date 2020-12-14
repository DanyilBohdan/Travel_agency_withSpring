package org.bohdan.web.controllers;

import org.bohdan.model.general.OrderTours;
import org.bohdan.web.Path;
import org.bohdan.web.services.OrderService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import javax.servlet.http.HttpSession;

import java.util.Arrays;
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

    private final OrderController orderController = new OrderController(orderService, session);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

    @Test
    public void getViewOrdersTest() throws Exception {

        Date d = new Date();
        Object date = new java.sql.Timestamp(d.getTime());

        OrderTours first = OrderTours.createOrderTour(1,1,"Test1","testType1","testCountry1",
                "paid", "test1@gmail.com",555, 4, 5, date, 1, 2, date);

        OrderTours second = OrderTours.createOrderTour(2,2,"Test2","testType2","testCountry2",
                "registered", "test2@gmail.com",555, 3, 3, date, 2, 3, date);

        session.setAttribute("localeDef", "EN");
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

        OrderTours first = OrderTours.createOrderTour(1,1,"Test1","testType1","testCountry1",
                "paid", "test1@gmail.com",555, 4, 5, date, 1, 2, date);

        OrderTours second = OrderTours.createOrderTour(2,2,"Test2","testType2","testCountry2",
                "registered", "test2@gmail.com",555, 3, 3, date, 2, 3, date);

        when(this.orderService.searchByStatusOrder("EN", search))
                .thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/order/view/searchByStatus").param("searchStatus", search))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin/listOrdersAdmin"))
                .andExpect(forwardedUrl("admin/listOrdersAdmin"))
                .andExpect(model().attribute("selectDef", is("paid")));
//                .andExpect(model().attribute("orders", hasSize(1)))
//                .andExpect(model().attribute("orders", hasItem(
//                        allOf(
//                                hasProperty("status", is(search))
//                        )
//                )));
    }

    @Test
    public void updateStatusOrderTest() throws Exception {

        when(this.orderService.updateStatusOrder(1, "paid"))
                .thenReturn(Path.COMMAND_LIST_ORDERS);

        mockMvc.perform(post("/order/view/update/status")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("order/view/update/status"))
                .andExpect(forwardedUrl("order/view/update/status"));
    }

}
