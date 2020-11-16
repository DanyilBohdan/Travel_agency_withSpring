package org.bohdan.db.DAO;

import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.ConnectionPool;
import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.model.general.ListBean;
import org.bohdan.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object for Country entity and ListBean
 */

@Repository
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

    private DataSource dataSource;

    @Autowired
    public CountryDao(ConnectionFactory connectionFactory) {
        if (connectionFactory.getClass() == ConnectionPool.class) {
            this.dataSource = ConnectionPool.getDataSource();
        }
        if (connectionFactory.getClass() == DBManager.class) {
            this.dataSource = DBManager.getDataSource();
        }
    }

    private Country mapCountry(ResultSet rs) throws SQLException {
        Country country = new Country();
        country.setId(rs.getInt(Fields.ID));
        country.setNameEn(rs.getString(Fields.NAME_EN));
        country.setNameRu(rs.getString(Fields.NAME_RU));
        return country;
    }

    private ListBean mapListBean(ResultSet rs) throws SQLException {
        ListBean typeTour = new ListBean();
        typeTour.setId(rs.getInt(Fields.ID));
        typeTour.setName(rs.getString(Fields.NAME));
        return typeTour;
    }

    /**
     * Return all countries by locale
     *
     * @param locale - locale (EN/RU)
     * @return list listBean entity
     */
    public List<ListBean> findByLocale(String locale) {
        if (locale.equals("EN")) {
            return findAllLocale(SQL_FIND_ALL_COUNTRY_TOUR_EN);
        }
        if (locale.equals("RU")) {
            return findAllLocale(SQL_FIND_ALL_COUNTRY_TOUR_RU);
        }
        return null;
    }

    /**
     * Return all countries by locale
     *
     * @param sql - SQL code
     * @return list listBean entity
     */
    private List<ListBean> findAllLocale(String sql) {
        List<ListBean> countries = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                countries.add(mapListBean(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return countries;
    }

    /**
     * Return all countries by locale
     *
     * @return list countries entity
     */
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
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

    /**
     * Return country by id
     *
     * @param id - id country
     * @return country
     */
    public Country findEntityById(Integer id) {
        Country country = null;
        try (Connection con = dataSource.getConnection();
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

    /**
     * Return country by name
     *
     * @param name - name country
     * @return country
     */
    public Country findByName(String name) {
        Country country = null;
        try (Connection con = dataSource.getConnection();
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

    /**
     * Delete country by id
     *
     * @param id - id country for deletion
     *
     * @return true - if the deletion was successful,
     *         false - if the deletion was unsuccessful
     */
    public boolean delete(Integer id) {
        try (Connection con = dataSource.getConnection();
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

    /**
     * Create country
     *
     * @param entity - country entity for create
     * @return true - if the creation was successful,
     *         false - if the creation was unsuccessful
     */
    public boolean create(Country entity) {
        ResultSet rs;
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_INSERT_COUNTRY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getNameEn());
            statement.setString(2, entity.getNameRu());
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

    /**
     * Update country
     *
     * @param entity - update entity
     * @return true - if the update entity was successful,
     *         false - if the update entity was unsuccessful
     */
    public boolean update(Country entity) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_COUNTRY)) {
            statement.setString(1, entity.getNameEn());
            statement.setString(2, entity.getNameRu());
            statement.setInt(3, entity.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
