package org.bohdan.db.DAO;

import org.apache.log4j.Logger;
import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.ConnectionPool;
import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.model.general.TourView;
import org.bohdan.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for Tour entity, TourView bean
 */
@Repository
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
            "UPDATE tour SET discount = ? WHERE id = ?";

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


    private static String filter;

    private DataSource dataSource;

    @Autowired
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

    /**
     * Calculating a percentage of the price
     *
     * @param price - main price
     * @param discount - discount tour
     * @return price
     */
    public static float changePrice(float price, float discount) {
        return price - ((discount * price) / 100);
    }

    /**
     * Set filter for SQL code
     *
     * @param check - check 1 or 0
     */
    public void setFilter(int check) {
        filter = FILTER_DATE_ADMIN;
        if (check == 1) {
            filter = FILTER_LIMIT_DATE;
        }
        logger.info("Log: filter --> " + filter);
    }

    /**
     * Return count tours
     *
     * @return count tours
     */
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

    /**
     * Return all tours
     *
     * @return list tours
     */
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

    /**
     * Return entity tour by id
     *
     * @param id - id tour
     * @return tour entity
     */
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

    /**
     * Return list tours by locale
     *
     * @param locale - locale
     * @param start - start limit
     * @param total - total limit
     * @return list tours
     */
    public List<TourView> findAllByLocale(String locale, int start, int total) {
        if (locale.equals("EN")) {
            return findAll(SQL_FIND_ALL_EN + filter, start, total);
        }
        if (locale.equals("RU")) {
            return findAll(SQL_FIND_ALL_RU + filter, start, total);
        }
        return null;
    }

    /**
     * Return list tours by locale
     *
     * @param sql - SQL code
     * @param start - start limit
     * @param total - total limit
     * @return list tours
     */
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

    /**
     * Return tour by id
     *
     * @param locale - locale
     * @param id - id tour
     * @return TourView bean
     */
    public TourView findByIdLocale(String locale, Integer id) {
        if (locale.equals("EN")) {
            return findById(SQL_FIND_ID_EN, id);
        }
        if (locale.equals("RU")) {
            return findById(SQL_FIND_ID_RU, id);
        }
        return null;
    }

    /**
     * Return tour by id
     *
     * @param sql - SQL code
     * @param id - id tour
     * @return TourView bean
     */
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

    /**
     * Search tours by variable
     *
     * @param locale - locale
     * @param var - name for search
     * @param start - start limit
     * @param total - total limit
     * @return list tours bean
     */
    public List<TourView> searchEntity(String locale, String var, int start, int total) {
        if (locale.equals("EN")) {
            return searchEntityByVar(SQL_FIND_BY_NAME_EN + filter, var, start, total);
        }
        if (locale.equals("RU")) {
            return searchEntityByVar(SQL_FIND_BY_NAME_RU + filter, var, start, total);
        }
        return null;
    }

    /**
     * Search tours by variable
     *
     * @param sql - SQL code by locale
     * @param var - name for search
     * @param start - start limit
     * @param total - total limit
     * @return list tours bean
     */
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

    /**
     * Search tours by type
     *
     * @param locale - locale
     * @param var - type for search
     * @param start - start limit
     * @param total - total limit
     * @return list tours bean
     */
    public List<TourView> findAllByTypeLocale(String locale, String var, int start, int total) {
        if (locale.equals("EN")) {
            return findAllVarBy(SQL_FIND_BY_TYPE_EN + filter, var, start, total);
        }
        if (locale.equals("RU")) {
            return findAllVarBy(SQL_FIND_BY_TYPE_RU + filter, var, start, total);
        }
        return null;
    }

    /**
     * Search tours by country
     *
     * @param locale - locale
     * @param var - country for search
     * @param start - start limit
     * @param total - total limit
     * @return list tours bean
     */
    public List<TourView> findAllByCountryLocale(String locale, String var, int start, int total) {
        if (locale.equals("EN")) {
            return findAllVarBy(SQL_FIND_BY_COUNTRY_EN + filter, var, start, total);
        }
        if (locale.equals("RU")) {
            return findAllVarBy(SQL_FIND_BY_COUNTRY_RU + filter, var, start, total);
        }
        return null;
    }

    /**
     * Search tours by variable
     *
     * @param sql - SQL code for locale
     * @param variable - variable for search
     * @param start - start limit
     * @param total - total limit
     * @return list tours bean
     */
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

    /**
     * Search tours by range
     *
     * @param by - method search
     * @param locale - locale
     * @param varFirst - first range
     * @param varLast - last range
     * @param start - start limit
     * @param total - total limit
     * @return list TourView bean
     */
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

    /**
     * Search tours by price
     *
     * @param locale - locale
     * @param varFirst - first range
     * @param varLast - last range
     * @param start - start limit
     * @param total - total limit
     * @return list TourView bean
     */
    private List<TourView> findAllByPriceLocale(String locale, String varFirst, String varLast, int start, int total) {
        if (locale.equals("EN")) {
            return findAllByRange(SQL_FIND_BY_PRICE_EN + filter, varFirst, varLast, start, total);
        }
        if (locale.equals("RU")) {
            return findAllByRange(SQL_FIND_BY_PRICE_RU + filter, varFirst, varLast, start, total);
        }
        return null;
    }

    /**
     * Search tours by count people
     *
     * @param locale - locale
     * @param varFirst - first range
     * @param varLast - last range
     * @param start - start limit
     * @param total - total limit
     * @return list TourView bean
     */
    private List<TourView> findAllByCountLocale(String locale, String varFirst, String varLast, int start, int total) {
        if (locale.equals("EN")) {
            return findAllByRange(SQL_FIND_BY_COUNT_EN + filter, varFirst, varLast, start, total);
        }
        if (locale.equals("RU")) {
            return findAllByRange(SQL_FIND_BY_COUNT_RU + filter, varFirst, varLast, start, total);
        }
        return null;
    }

    /**
     * Search tours by mark hotel
     *
     * @param locale - locale
     * @param varFirst - first range
     * @param varLast - last range
     * @param start - start limit
     * @param total - total limit
     * @return list TourView bean
     */
    private List<TourView> findAllByMarkLocale(String locale, String varFirst, String varLast, int start, int total) {
        if (locale.equals("EN")) {
            return findAllByRange(SQL_FIND_BY_MARK_EN + filter, varFirst, varLast, start, total);
        }
        if (locale.equals("RU")) {
            return findAllByRange(SQL_FIND_BY_MARK_RU + filter, varFirst, varLast, start, total);
        }
        return null;
    }

    /**
     * Search tours by range
     *
     * @param sql - SQL code for locale
     * @param varFirst - first range
     * @param varLast - last range
     * @param start - start limit
     * @param total - total limit
     * @return list TourView bean
     */
    private List<TourView> findAllByRange(String sql, String varFirst, String varLast, int start, int total) {
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

    /**
     * Create tour
     *
     * @param entity - tour entity for create
     * @return true - if the creation tour was successful,
     *         false - if the creation tour was unsuccessful
     */
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

    /**
     * Update tour
     *
     * @param entity - update tour entity
     * @return true - if the update entity tour was successful,
     *         false - if the update entity tour was unsuccessful
     */
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

    /**
     * Update discount tour
     *
     * @param id - id tour entity
     * @param discount - discount tour entity
     * @return true - if the update status tour was successful,
     *         false - if the update status tour was unsuccessful
     */
    public boolean updateDiscount(float discount, Integer id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_TOUR_DISCOUNT)) {
            statement.setFloat(1, discount);
            statement.setInt(2, id);
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

    /**
     * Delete tour by id
     *
     * @param id - id Tour for deletion
     *
     * @return true - if the deletion tour was successful,
     *         false - if the deletion tour was unsuccessful
     */
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
        tour.setNameEn(rs.getString(Fields.NAME_EN));
        tour.setNameRu(rs.getString(Fields.NAME_RU));
        tour.setDescEn(rs.getString(Fields.DESCRIPTION_EN));
        tour.setDescRu(rs.getString(Fields.DESCRIPTION_RU));
        tour.setCountPeople(rs.getInt(Fields.COUNT_PEOPLE));
        tour.setPrice(changePrice(rs.getFloat(Fields.PRICE), rs.getFloat(Fields.DISCOUNT)));
        tour.setMarkHotel(rs.getInt(Fields.MARK_HOTEL));
        tour.setStartDate(rs.getDate(Fields.START_DATE));
        tour.setDays(rs.getInt(Fields.DAYS));
        tour.setDiscount(rs.getFloat(Fields.DISCOUNT));
        tour.setTypeTourId(rs.getInt(Fields.TYPE_TOUR_ID));
        tour.setCountryId(rs.getInt(Fields.COUNTRY_ID));
        return tour;
    }

    private TourView mapTourView(ResultSet rs) throws SQLException {
        TourView tourView = new TourView();
        tourView.setId(rs.getInt(Fields.ID));
        tourView.setName(rs.getString(Fields.NAME));
        tourView.setType(rs.getString(Fields.TYPE));
        tourView.setCountry(rs.getString(Fields.COUNTRY));
        tourView.setPrice(changePrice(rs.getFloat(Fields.PRICE), rs.getFloat(Fields.DISCOUNT)));
        tourView.setDescription(rs.getString(Fields.DESCRIPTION));
        tourView.setCountPeople(rs.getInt(Fields.COUNT_PEOPLE));
        tourView.setMarkHotel(rs.getInt(Fields.MARK_HOTEL));
        tourView.setStartDate(rs.getDate(Fields.START_DATE));
        tourView.setDays(rs.getInt(Fields.DAYS));
        tourView.setDiscount(rs.getFloat(Fields.DISCOUNT));
        return tourView;
    }

    private void setStmtTour(Tour entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getNameEn());
        statement.setString(2, entity.getNameRu());
        statement.setString(3, entity.getDescEn());
        statement.setString(4, entity.getDescRu());
        statement.setFloat(5, entity.getPrice());
        statement.setInt(6, entity.getCountPeople());
        statement.setInt(7, entity.getMarkHotel());
        statement.setDate(8, (Date) entity.getStartDate());
        statement.setInt(9, entity.getDays());
        statement.setFloat(10, entity.getDiscount());
        statement.setInt(11, entity.getTypeTourId());
        statement.setInt(12, entity.getCountryId());
    }

}
