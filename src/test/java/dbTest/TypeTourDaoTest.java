package dbTest;

import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.db.DBManager;
import org.bohdan.model.general.ListBean;
import org.bohdan.model.TypeTour;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.util.List;

public class TypeTourDaoTest {

    private ConnectionFactory connectionFactory;
    private TypeTourDao typeTourDao;

    @Before
    public void setUp() throws NamingException {
        connectionFactory = DBManager.getInstance();
        typeTourDao = new TypeTourDao(connectionFactory);
    }

    @Test
    public void findByLocaleTest() {
        List<ListBean> listBeansExpectedEN = typeTourDao.findByLocale("EN");
        List<ListBean> listBeansExpectedRU = typeTourDao.findByLocale("RU");
        for (int i = 0; i < listBeansExpectedEN.size(); i++) {
            Assert.assertEquals(listBeansExpectedEN.get(i).getId(), listBeansExpectedRU.get(i).getId());
        }
    }

    @Test
    public void findAllTest() {
        List<TypeTour> listTypeTourExpected = typeTourDao.findAll();

        Assert.assertEquals(listTypeTourExpected.get(0).getId(), typeTourDao.findAll().get(0).getId());
    }

    @Test
    public void findByIdAndFindByNameAndCreateTestAndDeleteTest() {
        TypeTour testTypeTour = TypeTour.create("TestTypeTour", "ТестТипТура");
        boolean checkCreate = typeTourDao.create(testTypeTour);
        TypeTour typeTourExpected = null;
        if (checkCreate) {
            List<TypeTour> listTypeTour = typeTourDao.findAll();
            typeTourExpected = listTypeTour.get(listTypeTour.size()-1);
        }
        Assert.assertEquals(typeTourExpected.getId(), testTypeTour.getId());
        TypeTour typeTourDel = typeTourDao.findByName(testTypeTour.getNameEn());
        boolean checkDel = typeTourDao.delete(typeTourDel);
        Assert.assertTrue(checkDel);
    }

    @Test
    public void countryUpdateTest() {
        TypeTour testTypeTour = TypeTour.create("TestTypeTour", "ТестТипТура");
        TypeTour updateTypeTour = TypeTour.create("TestTypeTourEdit", "ТестТипТураРедактирование");
        boolean checkCreate = typeTourDao.create(testTypeTour);
        if (checkCreate) {
            List<TypeTour> listTypeTour = typeTourDao.findAll();
            int id = listTypeTour.get(listTypeTour.size()-1).getId();
            updateTypeTour.setId(id);
            boolean check = typeTourDao.update(updateTypeTour);
            Assert.assertTrue(check);
            testTypeTour.setId(id);
        }
        Assert.assertEquals(updateTypeTour.getId(), typeTourDao.findByName("TestTypeTourEdit").getId());
        Assert.assertEquals(testTypeTour.getId(), updateTypeTour.getId());
        boolean checkDel = typeTourDao.delete(updateTypeTour);
        Assert.assertTrue(checkDel);
    }
}
