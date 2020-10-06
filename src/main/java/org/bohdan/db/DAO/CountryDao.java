package org.bohdan.db.DAO;

import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.db.entity.Country;
import org.bohdan.db.entity.TypeTour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDao {

    private static final String SQL_FIND_ALL_COUNTRY_TOUR =
            "SELECT * FROM country";

    private static final String SQL_FIND_ENTITY_BY_ID_COUNTRY_TOUR =
            "SELECT * FROM country WHERE id = ?";

    private static final String SQL_FIND_ENTITY_BY_NAME =
            "SELECT * FROM country WHERE name_en = ? or name_ru = ?";

    private static final String SQL_DELETE_COUNTRY_BY_ID =
            "DELETE FROM country WHERE id = ?";

    private static final String SQL_INSERT_COUNTRY =
            "INSERT INTO country (name_en, name_ru) VALUES (?, ?)";

    private static final String SQL_UPDATE_COUNTRY =
            "UPDATE country SET name_en = ?, name_ru = ? WHERE id = ?";

    private Country mapCountry(ResultSet rs) throws SQLException {
        Country country = new Country();
        country.setId(rs.getInt(Fields.ID));
        country.setName_en(rs.getString(Fields.NAME_EN));
        country.setName_ru(rs.getString(Fields.NAME_RU));
        return country;
    }

    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_FIND_ALL_COUNTRY_TOUR);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                countries.add(mapCountry(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return countries;
    }

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

    public Country findByName(String name) {
        Country country = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_FIND_ENTITY_BY_NAME)) {
            statement.setString(1, name);
            statement.setString(2, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                country = mapCountry(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return country;
    }

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

    public boolean delete(Country entity) {
        return delete(entity.getId());
    }

    public boolean create(Country entity) {
        //boolean res = false;
        ResultSet rs;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_INSERT_COUNTRY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName_en());
            statement.setString(2, entity.getName_ru());
            if (statement.executeUpdate() > 0) {
                rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    entity.setId(rs.getInt(1));
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Country update(Country entity) {
        Country country = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_COUNTRY)) {
            statement.setString(1, entity.getName_en());
            statement.setString(2, entity.getName_ru());
            statement.setInt(3, entity.getId());
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
