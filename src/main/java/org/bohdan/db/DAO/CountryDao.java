package org.bohdan.db.DAO;

import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.db.bean.ListBean;
import org.bohdan.db.entity.Country;
import org.bohdan.db.entity.TypeTour;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDao {

    private static final String SQL_FIND_ALL_COUNTRY_TOUR =
            "SELECT * FROM country";

    private static final String SQL_FIND_ALL_COUNTRY_TOUR_EN =
            "SELECT country.id, country.name_en AS name FROM country";

    private static final String SQL_FIND_ALL_COUNTRY_TOUR_RU =
            "SELECT country.id, country.name_ru AS name FROM country";

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

    private ListBean mapListBean(ResultSet rs) throws SQLException {
        ListBean typeTour = new ListBean();
        typeTour.setId(rs.getInt(Fields.ID));
        typeTour.setName(rs.getString(Fields.NAME));
        return typeTour;
    }

    public List<ListBean> findByLocale(String locale) {
        if (locale.equals("EN")) {
            return findAllLocale(SQL_FIND_ALL_COUNTRY_TOUR_EN);
        }
        if (locale.equals("RU")) {
            return findAllLocale(SQL_FIND_ALL_COUNTRY_TOUR_RU);
        }
        return null;
    }

    private List<ListBean> findAllLocale(String sql) {
        List<ListBean> typeTours = new ArrayList<>();
        try (Connection con = DBManager.getInstance().getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                typeTours.add(mapListBean(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return typeTours;
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

    public boolean update(Country entity) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_COUNTRY)) {
            statement.setString(1, entity.getName_en());
            statement.setString(2, entity.getName_ru());
            statement.setInt(3, entity.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
