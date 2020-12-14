package org.bohdan.web.services;

import org.bohdan.db.DAO.*;
import org.bohdan.model.general.TourView;
import org.bohdan.model.general.UserRole;
import org.bohdan.web.services.impl.TourServiceImpl;
import org.bohdan.web.services.impl.UserServiceImpl;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private final OrderDao orderDao = mock(OrderDao.class);

    private final UserDao userDao = mock(UserDao.class);

    private final UserService userService = new UserServiceImpl(userDao, orderDao);

    @Test
    public void viewToursTest() throws Exception {
        List<UserRole> users = generateUser(3);
        doReturn(users).when(userDao).findUsersRole();

        List<UserRole> usersRole = userService.getListUsers();

        verify(userDao).findUsersRole();
        assertThat(usersRole, hasSize(3));

        users.forEach(
                user -> assertThat(usersRole, hasItem(
                        allOf(
                                hasProperty("id", is(user.getId())),
                                hasProperty("username", is(user.getUsername())),
                                hasProperty("password", is(user.getPassword())),
                                hasProperty("login", is(user.getLogin())),
                                hasProperty("phoneNumber", is(user.getPhoneNumber())),
                                hasProperty("status", is(user.isStatus())),
                                hasProperty("role", is(user.getRole()))
                        )))
        );
    }

    private List<UserRole> generateUser(int count) throws ParseException {
        List<UserRole> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            UserRole user = UserRole.create(i,"testUser" + i, "1111", "testUser"+i+"@gmail.com",
                    "(123) 12345612", true, "user");
            users.add(user);
        }
        return users;
    }
}
