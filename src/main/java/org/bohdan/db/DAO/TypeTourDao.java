package org.bohdan.db.DAO;

import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.db.entity.TypeTour;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeTourDao {

    private static final String SQL_FIND_ALL_TYPE_TOUR =
            "SELECT * FROM type_tour";

    private static final String SQL_FIND_ENTITY_BY_ID_TYPE_TOUR =
            "SELECT * FROM type_tour WHERE id = ?";

    private static final String SQL_FIND_ENTITY_BY_NAME =
            "SELECT * FROM type_tour WHERE name_en = ? or name_ru = ?";

    private static final String SQL_DELETE_TYPE_TOUR_BY_ID =
            "DELETE FROM type_tour WHERE id = ?";

    private static final String SQL_INSERT_TYPE_TOUR =
            "INSERT INTO type_tour (name_en, name_ru) VALUES (?, ?)";

    private static final String SQL_UPDATE_TYPE_TOUR =
            "UPDATE type_tour SET name_en = ?, name_ru = ? WHERE id = ?";

    private TypeTour mapTypeTour(ResultSet rs) throws SQLException {
        TypeTour typeTour = new TypeTour();
        typeTour.setId(rs.getInt(Fields.ID));
        typeTour.setName_en(rs.getString(Fields.NAME_EN));
        typeTour.setName_ru(rs.getString(Fields.NAME_RU));
        return typeTour;
    }

    public List<TypeTour> findAll() {
        List<TypeTour> typeTours = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(SQL_FIND_ALL_TYPE_TOUR)) {

            while (rs.next()) {
                typeTours.add(mapTypeTour(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return typeTours;
    }

    public TypeTour findEntityById(Integer id) {
        TypeTour typeTour = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_FIND_ENTITY_BY_ID_TYPE_TOUR)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                typeTour = mapTypeTour(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return typeTour;
    }

    public TypeTour findByName(String name) {
        TypeTour typeTour = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_FIND_ENTITY_BY_NAME)) {
            statement.setString(1, name);
            statement.setString(2, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                typeTour = mapTypeTour(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return typeTour;
    }

    public boolean delete(Integer id) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_DELETE_TYPE_TOUR_BY_ID);) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(TypeTour entity) {
        return delete(entity.getId());
    }

    public boolean create(TypeTour entity) {
        boolean res = false;
        ResultSet rs;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_INSERT_TYPE_TOUR, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName_en());
            statement.setString(2, entity.getName_ru());
            if (statement.executeUpdate() > 0) {
                rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    entity.setId(rs.getInt(1));
                    res = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public TypeTour update(TypeTour entity) {
        TypeTour typeTour = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_TYPE_TOUR)) {
            statement.setString(1, entity.getName_en());
            statement.setString(2, entity.getName_ru());
            statement.setInt(3, entity.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                typeTour = mapTypeTour(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return typeTour;
    }
}
