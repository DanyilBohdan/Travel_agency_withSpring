package org.finalproject.bohdan.db;

import org.finalproject.bohdan.db.entity.TypeTour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeTourDao {

    private static final String SQL_FIND_ALL_TYPE_TOUR =
            "SELECT * FROM type_tour";

    public List<TypeTour> findAllTypeTour() {
        List<TypeTour> typeTours = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_TYPE_TOUR)) {

            while (rs.next()) {
                typeTours.add(mapTypeTour(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return typeTours;
    }

    private TypeTour mapTypeTour(ResultSet rs) throws SQLException {
        TypeTour typeTour = new TypeTour();
        typeTour.setId(rs.getInt(Fields.ID));
        typeTour.setName(rs.getString(Fields.NAME));
        return typeTour;
    }

}
