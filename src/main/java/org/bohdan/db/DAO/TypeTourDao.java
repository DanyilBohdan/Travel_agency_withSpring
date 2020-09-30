package org.bohdan.db.DAO;

import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.db.entity.TypeTour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeTourDao extends AbstractDAO<Integer, TypeTour> {

    private static final String SQL_FIND_ALL_TYPE_TOUR =
            "SELECT * FROM type_tour";

    private static final String SQL_FIND_ENTITY_BY_ID_TYPE_TOUR =
            "SELECT * FROM type_tour WHERE id = ?";

    private static final String SQL_DELETE_TYPE_TOUR_BY_ID =
            "DELETE FROM type_tour WHERE id = ?";

    private static final String SQL_INSERT_TYPE_TOUR =
            "INSERT INTO type_tour (name) VALUES (?)";

    private static final String SQL_UPDATE_TYPE_TOUR =
            "UPDATE type_tour SET name = ? WHERE id = ?";

    private TypeTour mapTypeTour(ResultSet rs) throws SQLException {
        TypeTour typeTour = new TypeTour();
        typeTour.setId(rs.getInt(Fields.ID));
        typeTour.setName(rs.getString(Fields.NAME));
        return typeTour;
    }

    @Override
    public List<TypeTour> findAll() {
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

    @Override
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

    @Override
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

    @Override
    public boolean delete(TypeTour entity) {
        return delete(entity.getId());
    }

    @Override
    public boolean create(TypeTour entity) {
        boolean res = false;
        ResultSet rs = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_INSERT_TYPE_TOUR, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
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

    @Override
    public TypeTour update(TypeTour entity) {
        TypeTour typeTour = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_TYPE_TOUR)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());
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
