package org.bohdan.db.DAO;

import org.apache.log4j.Logger;
import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.ConnectionPool;
import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.db.bean.TourView;
import org.bohdan.db.bean.UserRole;
import org.bohdan.db.entity.Order;
import org.bohdan.db.entity.Tour;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDao {

    private static final Logger logger = Logger.getLogger(TourDao.class);

    private static final String FILTER_LIMIT_DATE = "and (tour.start_date - curdate()) > 5\n" +
            "limit ?, ?";

    private static final String FILTER_DATE_ADMIN = "limit ?, ?";

    public static final String SQL_INSERT_TOUR =
            "insert into tour (name_en, name_ru, desc_en, desc_ru, price, count_people, mark_hotel, \n" +
                    "start_date, days, discount, type_tour_id, country_id) values\n" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String SQL_UPDATE_TOUR =
            "UPDATE tour SET name_en = ?, name_ru = ?, desc_en = ?, desc_ru =?, price = ?, count_people = ?, mark_hotel = ?, " +
                    "start_date = ?, days = ?, discount = ?, type_tour_id = ?, country_id = ? WHERE id = ?";

    public static final String SQL_UPDATE_TOUR_DISCOUNT =
            "UPDATE tour SET discount = ?, price = ? WHERE id = ?";

    private static final String SQL_DELETE_TOUR_BY_ID =
            "DELETE FROM tour WHERE id = ?";

    private static final String SQL_FIND_ALL_TOUR =
            "select * from tour";

    private static final String SQL_FIND_COUNT_TOURS =
            "SELECT count(*) as count FROM tour";

    private static final String SQL_FIND_ALL_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, type_tour, country\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id\n";

    private static final String SQL_FIND_ALL_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id\n";

    private static final String SQL_FIND_ID_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, type_tour, country\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id and tour.id = ?";

    private static final String SQL_FIND_ID_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id and tour.id = ?";

    private static final String SQL_FIND_ID =
            "select * from tour WHERE tour.id = ?";

    private static final String SQL_FIND_BY_NAME_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and tour.name_en LIKE ?\n";

    private static final String SQL_FIND_BY_NAME_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and tour.name_ru LIKE ?\n";

    private static final String SQL_FIND_BY_TYPE_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and type_tour.name_en = ?\n";

    private static final String SQL_FIND_BY_TYPE_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and type_tour.name_ru = ?\n";

    private static final String SQL_FIND_BY_COUNTRY_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and country.name_en = ?\n";

    private static final String SQL_FIND_BY_COUNTRY_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and country.name_ru = ?\n";

    private static final String SQL_FIND_BY_PRICE_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and price > ? and price < ?\n";

    private static final String SQL_FIND_BY_PRICE_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and price > ? and price < ?\n";

    private static final String SQL_FIND_BY_COUNT_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and tour.count_people > ? and tour.count_people < ?\n";

    private static final String SQL_FIND_BY_COUNT_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and tour.count_people > ? and tour.count_people < ?\n";

    private static final String SQL_FIND_BY_MARK_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and tour.mark_hotel > ? and tour.mark_hotel < ?\n";

    private static final String SQL_FIND_BY_MARK_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and tour.mark_hotel > ? and tour.mark_hotel < ?\n";

    private static final String SQL_FIND_BY_DATE_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id " +
                    "and (tour.start_date - curdate()) < ?\n";

    private static final String SQL_FIND_BY_DATE_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and (tour.start_date - curdate()) > ?\n";


    private static String filter;

    private DataSource dataSource;

    public TourDao(ConnectionFactory connectionFactory) {
        if (connectionFactory.getClass() == ConnectionPool.class) {
            this.dataSource = ConnectionPool.getDataSource();
            logger.debug("Log: --> connection ========== ConnectionPool");
        }
        if (connectionFactory.getClass() == DBManager.class) {
            this.dataSource = DBManager.getDataSource();
            logger.debug("Log: --> connection ========== DBManager");
        }
    }

    public static float changePrice(float price, float discount) {
        return price - ((discount * price) / 100);
    }

    public static void setFilter(int check) {
        filter = FILTER_DATE_ADMIN;
        if (check == 1) {
            filter = FILTER_LIMIT_DATE;
        }
        logger.info("Log: filter --> " + filter);
    }

    public Integer findCountTours() {
        Integer count = null;

        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(SQL_FIND_COUNT_TOURS)) {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public List<Tour> findAllTour() {
        List<Tour> tours = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(SQL_FIND_ALL_TOUR)) {
            while (rs.next()) {
                tours.add(mapTour(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tours;
    }

    public Tour findIDTour(Integer id) {
        Tour tour = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_FIND_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                tour = mapTour(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tour;
    }

    public List<TourView> findAllByLocale(String locale, int start, int total) {
        if (locale.equals("EN")) {
            return findAll(SQL_FIND_ALL_EN + filter, start, total);
        }
        if (locale.equals("RU")) {
            return findAll(SQL_FIND_ALL_RU + filter, start, total);
        }
        return null;
    }

    private List<TourView> findAll(String sql, int start, int total) {
        List<TourView> tours = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);) {
            statement.setInt(1, start - 1);
            statement.setInt(2, total);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tours.add(mapTourView(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tours;
    }

    public TourView findByIdLocale(String locale, Integer id) {
        if (locale.equals("EN")) {
            return findById(SQL_FIND_ID_EN, id);
        }
        if (locale.equals("RU")) {
            return findById(SQL_FIND_ID_RU, id);
        }
        return null;
    }

    private TourView findById(String sql, Integer id) {
        TourView tour = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                tour = mapTourView(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tour;
    }

    public List<TourView> searchEntity(String locale, String var, int start, int total) {
        if (locale.equals("EN")) {
            return searchEntityByVar(SQL_FIND_BY_NAME_EN + filter, var, start, total);
        }
        if (locale.equals("RU")) {
            return searchEntityByVar(SQL_FIND_BY_NAME_RU + filter, var, start, total);
        }
        return null;
    }

    private List<TourView> searchEntityByVar(String sql, String var, int start, int total) {
        List<TourView> tours = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);) {
            statement.setString(1, "%" + var + "%");
            statement.setInt(2, start - 1);
            statement.setInt(3, total);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tours.add(mapTourView(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tours;
    }

    public List<TourView> findAllByTypeLocale(String locale, String var, int start, int total) {
        if (locale.equals("EN")) {
            return findAllVarBy(SQL_FIND_BY_TYPE_EN + filter, var, start, total);
        }
        if (locale.equals("RU")) {
            return findAllVarBy(SQL_FIND_BY_TYPE_RU + filter, var, start, total);
        }
        return null;
    }

    public List<TourView> findAllByCountryLocale(String locale, String var, int start, int total) {
        if (locale.equals("EN")) {
            return findAllVarBy(SQL_FIND_BY_COUNTRY_EN + filter, var, start, total);
        }
        if (locale.equals("RU")) {
            return findAllVarBy(SQL_FIND_BY_COUNTRY_RU + filter, var, start, total);
        }
        return null;
    }

    public List<TourView> findAllByDateLocale(String locale, String var, int start, int total) {
        if (locale.equals("EN")) {
            return findAllVarBy(SQL_FIND_BY_DATE_EN + filter, var, start, total);
        }
        if (locale.equals("RU")) {
            return findAllVarBy(SQL_FIND_BY_DATE_RU + filter, var, start, total);
        }
        return null;
    }

    private List<TourView> findAllVarBy(String sql, String variable, int start, int total) {
        List<TourView> tours = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, variable);
            statement.setInt(2, start - 1);
            statement.setInt(3, total);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tours.add(mapTourView(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tours;
    }

    public List<TourView> searchByRange(String by, String locale, String varFirst, String varLast,
                                        int start, int total) {
        if (by.equals("price")) {
            return findAllByPriceLocale(locale, varFirst, varLast, start, total);
        }
        if (by.equals("count_people")) {
            return findAllByCountLocale(locale, varFirst, varLast, start, total);
        }
        if (by.equals("mark_hotel")) {
            return findAllByMarkLocale(locale, varFirst, varLast, start, total);
        }
        return null;
    }

    private List<TourView> findAllByPriceLocale(String locale, String varFirst, String varLast, int start, int total) {
        if (locale.equals("EN")) {
            return findAllByRange(SQL_FIND_BY_PRICE_EN + filter, varFirst, varLast, start, total);
        }
        if (locale.equals("RU")) {
            return findAllByRange(SQL_FIND_BY_PRICE_RU + filter, varFirst, varLast, start, total);
        }
        return null;
    }

    private List<TourView> findAllByCountLocale(String locale, String varFirst, String varLast, int start, int total) {
        if (locale.equals("EN")) {
            return findAllByRange(SQL_FIND_BY_COUNT_EN + filter, varFirst, varLast, start, total);
        }
        if (locale.equals("RU")) {
            return findAllByRange(SQL_FIND_BY_COUNT_RU + filter, varFirst, varLast, start, total);
        }
        return null;
    }

    private List<TourView> findAllByMarkLocale(String locale, String varFirst, String varLast, int start, int total) {
        if (locale.equals("EN")) {
            return findAllByRange(SQL_FIND_BY_MARK_EN + filter, varFirst, varLast, start, total);
        }
        if (locale.equals("RU")) {
            return findAllByRange(SQL_FIND_BY_MARK_RU + filter, varFirst, varLast, start, total);
        }
        return null;
    }

    public List<TourView> findAllByRange(String sql, String varFirst, String varLast, int start, int total) {
        List<TourView> tours = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, varFirst);
            statement.setString(2, varLast);
            statement.setInt(3, start - 1);
            statement.setInt(4, total);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                tours.add(mapTourView(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tours;
    }

    public boolean create(Tour entity) {
        boolean res = false;
        ResultSet rs = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_INSERT_TOUR, Statement.RETURN_GENERATED_KEYS)) {
            setStmtTour(entity, statement);
            if (statement.executeUpdate() > 0) {
                rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    entity.setId(rs.getInt(1));
                    res = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBManager.close(rs);
        }
        return res;
    }

    public boolean update(Tour entity) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_TOUR)) {
            setStmtTour(entity, statement);
            statement.setInt(13, entity.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateDiscount(float discount, float price, Integer id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_TOUR_DISCOUNT)) {
            statement.setFloat(1, discount);
            statement.setFloat(2, changePrice(price, discount));
            statement.setInt(3, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //============================================
    //      DELETE TOUR
    //============================================
    public boolean delete(Tour entity) {
        return delete(entity.getId());
    }

    public boolean delete(Integer id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_DELETE_TOUR_BY_ID)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    //============================================

    private Tour mapTour(ResultSet rs) throws SQLException {
        Tour tour = new Tour();
        tour.setId(rs.getInt(Fields.ID));
        tour.setName_en(rs.getString(Fields.NAME_EN));
        tour.setName_ru(rs.getString(Fields.NAME_RU));
        tour.setDesc_en(rs.getString(Fields.DESCRIPTION_EN));
        tour.setDesc_ru(rs.getString(Fields.DESCRIPTION_RU));
        tour.setCount_people(rs.getInt(Fields.COUNT_PEOPLE));
        tour.setPrice(rs.getFloat(Fields.PRICE));
        tour.setMark_hotel(rs.getInt(Fields.MARK_HOTEL));
        tour.setStart_date(rs.getDate(Fields.START_DATE));
        tour.setDays(rs.getInt(Fields.DAYS));
        tour.setDiscount(rs.getFloat(Fields.DISCOUNT));
        tour.setType_tour_id(rs.getInt(Fields.TYPE_TOUR_ID));
        tour.setCountry_id(rs.getInt(Fields.COUNTRY_ID));
        return tour;
    }

    private TourView mapTourView(ResultSet rs) throws SQLException {
        TourView tourView = new TourView();
        tourView.setId(rs.getInt(Fields.ID));
        tourView.setName(rs.getString(Fields.NAME));
        tourView.setType(rs.getString(Fields.TYPE));
        tourView.setCountry(rs.getString(Fields.COUNTRY));
        tourView.setPrice(rs.getFloat(Fields.PRICE));
        tourView.setDescription(rs.getString(Fields.DESCRIPTION));
        tourView.setCount_people(rs.getInt(Fields.COUNT_PEOPLE));
        tourView.setMark_hotel(rs.getInt(Fields.MARK_HOTEL));
        tourView.setStart_date(rs.getDate(Fields.START_DATE));
        tourView.setDays(rs.getInt(Fields.DAYS));
        tourView.setDiscount(rs.getFloat(Fields.DISCOUNT));
        return tourView;
    }

    private void setStmtTour(Tour entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName_en());
        statement.setString(2, entity.getName_ru());
        statement.setString(3, entity.getDesc_en());
        statement.setString(4, entity.getDesc_ru());
        statement.setFloat(5, entity.getPrice());
        statement.setInt(6, entity.getCount_people());
        statement.setInt(7, entity.getMark_hotel());
        statement.setDate(8, (Date) entity.getStart_date());
        statement.setInt(9, entity.getDays());
        statement.setFloat(10, entity.getDiscount());
        statement.setInt(11, entity.getType_tour_id());
        statement.setInt(12, entity.getCountry_id());
    }

}
