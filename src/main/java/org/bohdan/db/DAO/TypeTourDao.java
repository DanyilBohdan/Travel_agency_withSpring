package org.bohdan.db.DAO;

import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.ConnectionPool;
import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.model.general.ListBean;
import org.bohdan.model.TypeTour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for TypeTour entity, ListBean
 */
@Repository
public class TypeTourDao {

    private static final String SQL_FIND_ALL_TYPE_TOUR =
            "SELECT * FROM type_tour";

    private static final String SQL_FIND_ALL_TYPE_TOUR_EN =
            "SELECT type_tour.id, type_tour.name_en AS name FROM type_tour";

    private static final String SQL_FIND_ALL_TYPE_TOUR_RU =
            "SELECT type_tour.id, type_tour.name_ru AS name FROM type_tour";

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

    private DataSource dataSource;

    @Autowired
    public TypeTourDao(ConnectionFactory connectionFactory) {
        if (connectionFactory.getClass() == ConnectionPool.class) {
            this.dataSource = ConnectionPool.getDataSource();
        }
        if (connectionFactory.getClass() == DBManager.class) {
            this.dataSource = DBManager.getDataSource();
        }
    }

    private TypeTour mapTypeTour(ResultSet rs) throws SQLException {
        TypeTour typeTour = new TypeTour();
        typeTour.setId(rs.getInt(Fields.ID));
        typeTour.setNameEn(rs.getString(Fields.NAME_EN));
        typeTour.setNameRu(rs.getString(Fields.NAME_RU));
        return typeTour;
    }

    private ListBean mapListBean(ResultSet rs) throws SQLException {
        ListBean typeTour = new ListBean();
        typeTour.setId(rs.getInt(Fields.ID));
        typeTour.setName(rs.getString(Fields.NAME));
        return typeTour;
    }

    /**
     * Return all typeis tours by locale
     *
     * @param locale - locale (EN/RU)
     * @return list listBean entity
     */
    public List<ListBean> findByLocale(String locale){
        if (locale.equals("EN")){
            return findAllLocale(SQL_FIND_ALL_TYPE_TOUR_EN);
        }
        if (locale.equals("RU")){
            return findAllLocale(SQL_FIND_ALL_TYPE_TOUR_RU);
        }
        return null;
    }

    /**
     * Return all typeis tours by locale
     *
     * @param sql - SQL code
     * @return list listBean entity
     */
    private List<ListBean> findAllLocale(String sql) {
        List<ListBean> typeTours = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
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

    /**
     * Return all typeis tours by locale
     *
     * @return list TypeTour entity
     */
    public List<TypeTour> findAll() {
        List<TypeTour> typeTours = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
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

    /**
     * Return type tour by id
     *
     * @param id - id type tour
     * @return type tour
     */
    public TypeTour findEntityById(Integer id) {
        TypeTour typeTour = null;
        try (Connection con = dataSource.getConnection();
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

    /**
     * Return type tour by name
     *
     * @param name - name type tour
     * @return type tour
     */
    public TypeTour findByName(String name) {
        TypeTour typeTour = null;
        try (Connection con = dataSource.getConnection();
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

    /**
     * Delete type tour by id
     *
     * @param id - id Type Tour for deletion
     *
     * @return true - if the deletion was successful,
     *         false - if the deletion was unsuccessful
     */
    public boolean delete(Integer id) {
        try (Connection con = dataSource.getConnection();
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

    /**
     * Create type tour
     *
     * @param entity - type tour entity for create
     * @return true - if the creation was successful,
     *         false - if the creation was unsuccessful
     */
    public boolean create(TypeTour entity) {
        boolean res = false;
        ResultSet rs;
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_INSERT_TYPE_TOUR, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getNameEn());
            statement.setString(2, entity.getNameRu());
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

    /**
     * Update type tour
     *
     * @param entity - update entity
     * @return true - if the update entity was successful,
     *         false - if the update entity was unsuccessful
     */
    public boolean update(TypeTour entity) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_TYPE_TOUR)) {
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
