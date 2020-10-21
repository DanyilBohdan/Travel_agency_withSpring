package dbTest;

import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.DAO.UserDao;
import org.bohdan.db.DBManager;
import org.bohdan.db.bean.UserRole;
import org.bohdan.db.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.List;

public class UserDaoTest {

    private ConnectionFactory connectionFactory;
    private UserDao userDao;

    @Before
    public void setUp() throws NamingException {
        connectionFactory = DBManager.getInstance();
        userDao = new UserDao(connectionFactory);
    }

    private User createUser() {
        User user = User.createUser("userTest", "1111", "userTest@test.com",
                "(123) 123-4567", true, 1);

        boolean check = userDao.create(user);

        Assert.assertTrue(check);

        return user;
    }

    private void deleteUser(Integer id) {
        boolean check = userDao.delete(id);

        Assert.assertTrue(check);
    }

    @Test
    public void findAllUsersTest() {
        List<User> listUsersExpected = userDao.findAll();

        Assert.assertEquals(listUsersExpected.get(0).getId(), userDao.findAll().get(0).getId());
    }

    @Test
    public void findAllUsersRoleTest() {
        List<UserRole> listUsersExpected = userDao.findUsersRole();

        Assert.assertEquals(listUsersExpected.get(0).getId(), userDao.findAll().get(0).getId());
    }

    @Test
    public void findByIdUserTest() {
        User user = createUser();

        User expectedUser = userDao.findEntityById(user.getId());

        Assert.assertEquals(expectedUser.getUsername(), user.getUsername());

        deleteUser(user.getId());
    }

    @Test
    public void findByLoginUserTest() {
        User user = createUser();

        User expectedUser = userDao.findEntityByLogin(user.getLogin());

        Assert.assertEquals(expectedUser.getLogin(), user.getLogin());

        deleteUser(user.getId());
    }

    @Test
    public void searchEntityUsernameTest() {
        User user = createUser();

        String by = "username";
        List<UserRole> expectedUser = userDao.searchEntity(by, "userTest");

        Assert.assertEquals(expectedUser.get(expectedUser.size()-1).getId(), user.getId());

        deleteUser(user.getId());
    }

    @Test
    public void searchEntityLoginTest() {
        User user = createUser();

        String by = "login";
        List<UserRole> expectedUser = userDao.searchEntity(by, "userTest@test.com");

        Assert.assertEquals(expectedUser.get(expectedUser.size()-1).getId(), user.getId());

        deleteUser(user.getId());
    }

    @Test
    public void searchEntityPhoneNumberTest() {
        User user = createUser();

        String by = "phone_number";
        List<UserRole> expectedUser = userDao.searchEntity(by, "(123) 123-4567");

        Assert.assertEquals(expectedUser.get(expectedUser.size()-1).getId(), user.getId());

        deleteUser(user.getId());
    }
}
