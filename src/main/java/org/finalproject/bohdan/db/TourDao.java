package org.finalproject.bohdan.db;

import org.finalproject.bohdan.db.entity.Tour;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TourDao {
    private static final String SQL_FIND_ALL_TOUR =
            "SELECT * FROM tour";

    public List<Tour> findAllTour() {
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
}
