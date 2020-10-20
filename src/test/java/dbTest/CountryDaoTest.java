package dbTest;

import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DBManager;
import org.bohdan.db.bean.ListBean;
import org.bohdan.db.entity.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.List;

public class CountryDaoTest {

    private ConnectionFactory connectionFactory;
    private CountryDao countryDao;

    @Before
    public void setUp() throws NamingException {
        connectionFactory = DBManager.getInstance();
        countryDao = new CountryDao(connectionFactory);
    }

    @Test
    public void findByLocaleTest() {
        List<ListBean> listBeansExpectedEN = countryDao.findByLocale("EN");
        List<ListBean> listBeansExpectedRU = countryDao.findByLocale("RU");
        for (int i = 0; i < listBeansExpectedEN.size(); i++) {
            Assert.assertEquals(listBeansExpectedEN.get(i).getId(), listBeansExpectedRU.get(i).getId());
        }
    }

    @Test
    public void findAllTest() {
        List<Country> listCountryExpected = countryDao.findAll();

        Assert.assertEquals(listCountryExpected.get(0).getId(), countryDao.findAll().get(0).getId());
    }

    @Test
    public void findByIdAndFindByNameAndCreateTestAndDeleteTest() {
        Country testCountry = Country.create("TestCountry", "ТестСтрана");
        boolean checkCreate = countryDao.create(testCountry);
        List<Country> listCountry = null;
        Country countryExpected = null;
        if (checkCreate) {
            listCountry = countryDao.findAll();
            countryExpected = listCountry.get(listCountry.size()-1);
        }
        Assert.assertEquals(countryExpected.getId(), testCountry.getId());
        Country countryDel = countryDao.findByName(testCountry.getName_en());
        boolean checkDel = countryDao.delete(countryDel);
        Assert.assertTrue(checkDel);
    }

    @Test
    public void countryUpdateTest() {
        Country testCountry = Country.create("TestCountry", "ТестСтрана");
        Country updateCountry = Country.create("TestCountryEdit", "ТестСтранаРедактирование");
        boolean checkCreate = countryDao.create(testCountry);
        if (checkCreate) {
            List<Country> listCountry = countryDao.findAll();
            int id = listCountry.get(listCountry.size()-1).getId();
            updateCountry.setId(id);
            boolean check = countryDao.update(updateCountry);
            Assert.assertTrue(check);
            testCountry.setId(id);
        }
        Assert.assertEquals(updateCountry.getId(), countryDao.findByName("TestCountryEdit").getId());
        Assert.assertEquals(testCountry.getId(), updateCountry.getId());
        boolean checkDel = countryDao.delete(updateCountry);
        Assert.assertTrue(checkDel);
    }

}
