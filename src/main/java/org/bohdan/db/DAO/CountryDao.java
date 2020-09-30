package org.bohdan.db.DAO;

import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.db.entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDao extends AbstractDAO<Integer, Country> {

    private static final String SQL_FIND_ALL_COUNTRY_TOUR =
            "SELECT * FROM country";

    private static final String SQL_FIND_ENTITY_BY_ID_COUNTRY_TOUR =
            "SELECT * FROM country WHERE id = ?";

    private static final String SQL_DELETE_COUNTRY_BY_ID =
            "DELETE FROM country WHERE id = ?";

    private static final String SQL_INSERT_COUNTRY =
            "INSERT INTO country (name) VALUES (?)";

    private static final String SQL_UPDATE_COUNTRY =
            "UPDATE country SET name = ? WHERE id = ?";

    private Country mapCountry(ResultSet rs) throws SQLException {
        Country country = new Country();
        country.setId(rs.getInt(Fields.ID));
        country.setName(rs.getString(Fields.NAME));
        return country;
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_COUNTRY_TOUR)) {

            while (rs.next()) {
                countries.add(mapCountry(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return countries;
    }

    @Override
    public Country findEntityById(Integer id) {
        Country country = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_FIND_ENTITY_BY_ID_COUNTRY_TOUR)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                country = mapCountry(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return country;
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_DELETE_COUNTRY_BY_ID);) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Country entity) {
        return delete(entity.getId());
    }

    @Override
    public boolean create(Country entity) {
        boolean res = false;
        ResultSet rs = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_INSERT_COUNTRY, Statement.RETURN_GENERATED_KEYS)) {
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
    public Country update(Country entity) {
        Country country = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_COUNTRY)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                country = mapCountry(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return country;
    }
}
