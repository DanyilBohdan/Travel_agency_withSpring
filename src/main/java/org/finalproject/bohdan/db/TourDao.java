package org.finalproject.bohdan.db;

import org.finalproject.bohdan.db.entity.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDao extends AbstractDAO<Integer, Tour> {
    private static final String SQL_FIND_ALL_TOUR =
            "SELECT * FROM tour";

    private static final String SQL_FIND_ENTITY_BY_ID_TOUR =
            "SELECT * FROM tour WHERE id = ?";

    public static final String SQL_DELETE_TOUR_BY_ID =
            "DELETE FROM tour WHERE id = ?";

    public static final String SQL_INSERT_TOUR =
            "insert into tour (name, country, price, count_people, description, mark_hotel, start_date, days, discount, type_tour_id) values " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

//    public static final String SQL_UPDATE_USER =
//            "UPDATE user SET username = ?, password = ?, email = ?, phone_number = ? WHERE id = ?";

    public static final String SQL_UPDATE_TOUR =
            "UPDATE user SET name = ?, country = ?, price = ?, count_people = ?, description = ?, " +
                    "mark_hotel = ?, start_date = ?, days = ?, discount = ?, type_tour_id = ? WHERE id = ?";


    private Tour mapTour(ResultSet rs) throws SQLException {
        Tour tour = new Tour();
        tour.setId(rs.getInt(Fields.ID));
        tour.setName(rs.getString(Fields.NAME));
        tour.setCountry(rs.getString(Fields.COUNTRY));
        tour.setPrice(rs.getFloat(Fields.PRICE));
        tour.setCount_people(rs.getInt(Fields.COUNT_PEOPLE));
        tour.setDescription(rs.getString(Fields.DESCRIPTION));
        tour.setMark_hotel(rs.getInt(Fields.MARK_HOTEL));
        tour.setStart_date(rs.getDate(Fields.START_DATE));
        tour.setDays(rs.getInt(Fields.DAYS));
        tour.setDiscount(rs.getInt(Fields.DISCOUNT));
        tour.setType_tour_id(rs.getInt(Fields.TYPE_TOUR_ID));
        return tour;
    }

    @Override
    public List<Tour> findAll() {
        List<Tour> tours = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_TOUR)) {

            while (rs.next()) {
                tours.add(mapTour(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tours;
    }

    @Override
    public Tour findEntityById(Integer id) {
        Tour tour = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_FIND_ENTITY_BY_ID_TOUR);) {
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

    @Override
    public boolean delete(Integer id) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_DELETE_TOUR_BY_ID);) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Tour entity) {
        return delete(entity.getId());
    }

    @Override
    public boolean create(Tour entity) {
        boolean res = false;

        ResultSet rs = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_INSERT_TOUR, Statement.RETURN_GENERATED_KEYS)) {
            addStatement(entity, statement);
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
            AbstractDAO.close(rs);
        }
        return res;
    }

    @Override
    public Tour update(Tour entity) {
        Tour tour = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_TOUR)) {
            addStatement(entity, statement);
            statement.setInt(11, entity.getType_tour_id());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                tour = mapTour(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tour;
    }

    private void addStatement(Tour entity, PreparedStatement statement) throws SQLException {
        int k = 1;
        statement.setString(k++, entity.getName());
        statement.setString(k++, entity.getCountry());
        statement.setFloat(k++, entity.getPrice());
        statement.setInt(k++, entity.getCount_people());
        statement.setString(k++, entity.getDescription());
        statement.setInt(k++, entity.getMark_hotel());
        statement.setDate(k++, (Date) entity.getStart_date());
        statement.setInt(k++, entity.getDays());
        statement.setFloat(k++, entity.getDiscount());
        statement.setInt(k++, entity.getType_tour_id());
    }
}
