package org.bohdan.db.DAO;

import org.apache.log4j.Logger;
import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.db.bean.TourView;
import org.bohdan.db.entity.Order;
import org.bohdan.db.entity.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDao {

    private static final Logger logger = Logger.getLogger(TourDao.class);

    public static final String SQL_INSERT_TOUR =
            "insert into tour (name_en, name_ru, desc_en, desc_ru, price, count_people, mark_hotel, \n" +
                    "start_date, days, discount, type_tour_id, country_id) values\n" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String SQL_UPDATE_TOUR =
            "UPDATE tour SET name_en = ?, name_ru = ?, desc_en = ?, desc_ru =?, price = ?, count_people = ?, mark_hotel = ?, " +
                    "start_date = ?, days = ?, discount = ?, type_tour_id = ?, country_id = ? WHERE id = ?";

    private static final String SQL_DELETE_TOUR_BY_ID =
            "DELETE FROM tour WHERE id = ?";

    private static final String SQL_FIND_ALL_TOUR =
            "select * from tour";

    private static final String SQL_FIND_ALL_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, type_tour, country\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id;";

    private static final String SQL_FIND_ALL_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id";

    private static final String SQL_FIND_ID =
            "select * from tour WHERE tour.id = ?";

    private static final String SQL_FIND_BY_TYPE_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and type_tour.name_en = ?";

    private static final String SQL_FIND_BY_TYPE_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and type_tour.name_ru = ?";

    private static final String SQL_FIND_BY_PRICE_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and price > ? and price < ?";

    private static final String SQL_FIND_BY_PRICE_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and price > ? and price < ?";

    private static final String SQL_FIND_BY_COUNT_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and tour.count_people > ? and tour.count_people < ?";

    private static final String SQL_FIND_BY_COUNT_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and tour.count_people > ? and tour.count_people < ?";

    private static final String SQL_FIND_BY_MARK_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and tour.mark_hotel > ? and tour.mark_hotel < ?";

    private static final String SQL_FIND_BY_MARK_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and tour.mark_hotel > ? and tour.mark_hotel < ?";

    private static final String SQL_FIND_BY_DATE_EN =
            "select tour.id, tour.name_en as name, type_tour.name_en as type, country.name_en as country, tour.price, \n" +
                    "tour.desc_en as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and (tour.start_date - curdate()) < ?";

    private static final String SQL_FIND_BY_DATE_RU =
            "select tour.id, tour.name_ru as name, type_tour.name_ru as type, country.name_ru as country, tour.price, \n" +
                    "tour.desc_ru as description, tour.count_people, tour.mark_hotel, tour.start_date, tour.days, tour.discount\n" +
                    "from tour, country, type_tour\n" +
                    "where type_tour.id = tour.type_tour_id and country.id = tour.country_id \n" +
                    "and (tour.start_date - curdate()) < ?";


    public List<Tour> findAllTour() {
        List<Tour> tours = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
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
        try (Connection con = DBManager.getInstance().getConnection();
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

    public List<TourView> findAllByLocale(String locale) {
        if (locale.equals("EN")) {
            return findAll(SQL_FIND_ALL_EN);
        }
        if (locale.equals("RU")) {
            return findAll(SQL_FIND_ALL_RU);
        }
        return null;
    }

    private List<TourView> findAll(String sql) {
        List<TourView> tours = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                tours.add(mapTourView(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tours;
    }

    public List<TourView> findAllByTypeLocale(String locale, String var) {
        if (locale.equals("EN")) {
            return findAllVarByType(SQL_FIND_BY_TYPE_EN, var);
        }
        if (locale.equals("RU")) {
            return findAllVarByType(SQL_FIND_BY_TYPE_RU, var);
        }
        return null;
    }

    public List<TourView> findAllByDateLocale(String locale, String var) {
        if (locale.equals("EN")) {
            return findAllVarByType(SQL_FIND_BY_DATE_EN, var);
        }
        if (locale.equals("RU")) {
            return findAllVarByType(SQL_FIND_BY_DATE_RU, var);
        }
        return null;
    }

    public List<TourView> findAllVarByType(String sql, String variable) {
        List<TourView> tours = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, variable);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                tours.add(mapTourView(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tours;
    }

    public List<TourView> findAllByPriceLocale(String locale, String varFirst, String varLast) {
        if (locale.equals("EN")) {
            return findAllByRange(SQL_FIND_BY_PRICE_EN, varFirst, varLast);
        }
        if (locale.equals("RU")) {
            return findAllByRange(SQL_FIND_BY_PRICE_RU, varFirst, varLast);
        }
        return null;
    }

    public List<TourView> findAllByCountLocale(String locale, String varFirst, String varLast) {
        if (locale.equals("EN")) {
            return findAllByRange(SQL_FIND_BY_COUNT_EN, varFirst, varLast);
        }
        if (locale.equals("RU")) {
            return findAllByRange(SQL_FIND_BY_COUNT_RU, varFirst, varLast);
        }
        return null;
    }

    public List<TourView> findAllByMarkLocale(String locale, String varFirst, String varLast) {
        if (locale.equals("EN")) {
            return findAllByRange(SQL_FIND_BY_MARK_EN, varFirst, varLast);
        }
        if (locale.equals("RU")) {
            return findAllByRange(SQL_FIND_BY_MARK_RU, varFirst, varLast);
        }
        return null;
    }

    public List<TourView> findAllByRange(String sql, String varFirst, String varLast) {
        List<TourView> tours = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, varFirst);
            statement.setString(2, varLast);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
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
        try (Connection con = DBManager.getInstance().getConnection();
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
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_TOUR)) {
            setStmtTour(entity, statement);
            statement.setFloat(13, entity.getId());
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
        try (Connection con = DBManager.getInstance().getConnection();
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
        tour.setPrice(rs.getFloat(Fields.PRICE));
        tour.setCount_people(rs.getInt(Fields.COUNT_PEOPLE));
        tour.setMark_hotel(rs.getInt(Fields.MARK_HOTEL));
        tour.setStart_date(rs.getDate(Fields.START_DATE));
        tour.setDays(rs.getInt(Fields.DAYS));
        tour.setDiscount(rs.getInt(Fields.DISCOUNT));
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
        tourView.setDiscount(rs.getInt(Fields.DISCOUNT));
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
