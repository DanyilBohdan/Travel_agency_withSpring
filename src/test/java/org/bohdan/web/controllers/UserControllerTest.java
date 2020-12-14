package org.bohdan.web.controllers;

import org.bohdan.model.User;
import org.bohdan.model.general.OrderTours;
import org.bohdan.model.general.UserRole;
import org.bohdan.web.Path;
import org.bohdan.web.services.UserService;
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
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private final UserService userService = mock(UserService.class);

    private final HttpSession session = mock(HttpSession.class);

    private final HttpServletRequest request = mock(HttpServletRequest.class);

    private final UserController userController = new UserController(userService, session);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    @Test
    public void viewUserAccountTest() throws Exception {
        
        User user = User.createUser("testUser", "1111", "test@gmail.com", "(123) 12345612", true, 1);
        user.setId(1);

        Date d = new Date();
        Object date = new java.sql.Timestamp(d.getTime());
        OrderTours first = OrderTours.createOrderTour(1, 1, "Test1", "testType1", "testCountry1",
                "paid", "test1@gmail.com", 555, 4, 5, date, 1, 2, date);
        OrderTours second = OrderTours.createOrderTour(2, 2, "Test2", "testType2", "testCountry2",
                "registered", "test2@gmail.com", 555, 3, 3, date, 2, 3, date);

        String lang = "EN";

        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("lang")).thenReturn(lang);
        when(request.getRequestURI()).thenReturn("/user/account");
        when(this.userService.accountUser(lang, user)).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/user/account"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(Path.ACCOUNT_USER))
                .andExpect(forwardedUrl(Path.ACCOUNT_USER))
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
    public void viewListUsersTest() throws Exception {

        UserRole user1 = UserRole.create(1,"testUser1", "1111", "testUser1@gmail.com",
                "(123) 12345612", true, "user");

        UserRole user2 = UserRole.create(2,"testUser2", "1111", "testUser2@gmail.com",
                "(123) 12345612", true, "user");

        when(userService.getListUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/user/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(Path.LIST_USERS_ADMIN))
                .andExpect(forwardedUrl(Path.LIST_USERS_ADMIN))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(user1.getId())),
                                hasProperty("login", is(user1.getLogin())),
                                hasProperty("username", is(user1.getUsername()))
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(user2.getId())),
                                hasProperty("login", is(user2.getLogin())),
                                hasProperty("username", is(user2.getUsername()))
                        )
                )));
    }

    @Test
    public void searchListUsersBySelectTest() throws Exception {

        UserRole user1 = UserRole.create(1,"testUser1", "1111", "testUser1@gmail.com",
                "(123) 12345612", true, "user");

        String searchText = "testUser1";
        String searchSelect = "username";

        when(request.getParameter("searchText")).thenReturn(searchText);
        when(request.getParameter("searchSelect")).thenReturn(searchSelect);
        when(this.userService.searchUser(searchSelect, searchText)).thenReturn(Collections.singletonList(user1));

        mockMvc.perform(get("/user/list/search")
                .param("searchText", searchText)
                .param("searchSelect", searchSelect))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(Path.LIST_USERS_ADMIN))
                .andExpect(forwardedUrl(Path.LIST_USERS_ADMIN))
                .andExpect(model().attribute("users", hasSize(1)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(user1.getId())),
                                hasProperty("login", is(user1.getLogin())),
                                hasProperty("username", is(user1.getUsername()))
                        )
                )));
    }
}
