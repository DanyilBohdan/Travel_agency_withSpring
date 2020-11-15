package org.bohdan.db.DAO;

import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.ConnectionPool;
import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.model.general.UserRole;
import org.bohdan.model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for User entity, UserRole bean
 */
@Repository
public class UserDao {

    private static final String SQL_FIND_ALL_USER =
            "SELECT * FROM user";

    private static final String SQL_FIND_ALL_USER_ROLE =
            "SELECT * FROM user, role WHERE user.role_id = role.id";

    private static final String SQL_SEARCH_USERS_BY_USERNAME =
            "SELECT * FROM user, role WHERE user.role_id = role.id and user.username LIKE ?";

    private static final String SQL_SEARCH_USERS_BY_LOGIN =
            "SELECT * FROM user, role WHERE user.role_id = role.id and user.login LIKE ?";

    private static final String SQL_SEARCH_USERS_BY_PHONE =
            "SELECT * FROM user, role WHERE user.role_id = role.id and user.phone_number LIKE ?";

    private static final String SQL_FIND_ENTITY_BY_ID_USER =
            "SELECT * FROM user WHERE id = ?";

    private static final String SQL_FIND_ENTITY_BY_LOGIN_USER =
            "SELECT * FROM user WHERE login = ?";

    public static final String SQL_DELETE_USER_BY_ID =
            "DELETE FROM user WHERE id = ?";

    public static final String SQL_INSERT_USER =
            "insert into user (username, password, login, phone_number, status, role_id) values " +
                    "(?, ?, ?, ?, ?, ?)";

    public static final String SQL_UPDATE_USER =
            "UPDATE user SET username = ?, password = ?, login = ?, phone_number = ?, status = ?, role_id = ? WHERE id = ?";

    public static final String SQL_UPDATE_STATUS =
            "UPDATE user SET status = ? WHERE id = ?";

    public static final String SQL_UPDATE_ROLE =
            "UPDATE user SET role_id = ? WHERE id = ?";

    private DataSource dataSource;

    public UserDao(ConnectionFactory connectionFactory) {
        if (connectionFactory.getClass() == ConnectionPool.class) {
            this.dataSource = ConnectionPool.getDataSource();
        }
        if (connectionFactory.getClass() == DBManager.class) {
            this.dataSource = DBManager.getDataSource();
        }
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ID));
        user.setUsername(rs.getString(Fields.USERNAME));
        user.setPassword(rs.getString(Fields.PASSWORD));
        user.setLogin(rs.getString(Fields.LOGIN));
        user.setPhoneNumber(rs.getString(Fields.PHONE_NUMBER));
        user.setStatus(rs.getBoolean(Fields.STATUS));
        user.setRoleId(rs.getInt(Fields.ROLE_ID));
        return user;
    }

    private UserRole mapUserRole(ResultSet rs) throws SQLException {
        UserRole user = new UserRole();
        user.setId(rs.getInt(Fields.ID));
        user.setUsername(rs.getString(Fields.USERNAME));
        user.setPassword(rs.getString(Fields.PASSWORD));
        user.setLogin(rs.getString(Fields.LOGIN));
        user.setPhoneNumber(rs.getString(Fields.PHONE_NUMBER));
        user.setStatus(rs.getBoolean(Fields.STATUS));
        user.setRole(rs.getString(Fields.NAME));
        return user;
    }

    /**
     * Returns all users.
     *
     * @return List of users entities.
     */
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_USER)) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    /**
     * Returns all users.
     *
     * @return List of usersRole entities.
     */
    public List<UserRole> findUsersRole() {
        List<UserRole> users = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_USER_ROLE)) {

            while (rs.next()) {
                users.add(mapUserRole(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    /**
     * Return one user by id.
     *
     * @param id
     *
     * @return User entity.
     */
    public User findEntityById(Integer id) {
        User user = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_FIND_ENTITY_BY_ID_USER);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = mapUser(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    /**
     * Return one user by login.
     *
     * @param login
     *
     * @return User entity.
     */
    public User findEntityByLogin(String login) {
        User user = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_FIND_ENTITY_BY_LOGIN_USER);) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = mapUser(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    /**
     * Search all users by username, login and phone number.
     *
     * @param by - by what principle to search
     * @param var - search variable
     *
     * @return List of usersRole entities.
     */
    public List<UserRole> searchEntity(String by, String var) {
        if (by.equals("username")) {
            return searchEntityByVar(SQL_SEARCH_USERS_BY_USERNAME, var);
        }
        if (by.equals("login")) {
            return searchEntityByVar(SQL_SEARCH_USERS_BY_LOGIN, var);
        }
        if (by.equals("phone_number")) {
            return searchEntityByVar(SQL_SEARCH_USERS_BY_PHONE, var);
        }
        return null;
    }

    /**
     * Search all users by username, login and phone number.
     *
     * @param sql - SQL code
     * @param var - search variable
     *
     * @return List of usersRole entities.
     */
    private List<UserRole> searchEntityByVar(String sql, String var) {
        List<UserRole> users = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);) {
            statement.setString(1, "%" + var + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(mapUserRole(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    /**
     * Delete user by id
     *
     * @param id - id User for deletion
     *
     * @return true - if the deletion was successful,
     *         false - if the deletion was unsuccessful
     */
    public boolean delete(Integer id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_DELETE_USER_BY_ID);) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(User entity) {
        return delete(entity.getId());
    }

    /**
     * Create user
     *
     * @param entity - user entity for create
     * @return true - if the creation was successful,
     *         false - if the creation was unsuccessful
     */
    public boolean create(User entity) {
        boolean res = false;

        ResultSet rs = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            statement.setString(k++, entity.getUsername());
            statement.setString(k++, entity.getPassword());
            statement.setString(k++, entity.getLogin());
            statement.setString(k++, entity.getPhoneNumber());
            statement.setBoolean(k++, entity.getStatus());
            statement.setInt(k++, entity.getRoleId());

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
            DBManager.close(rs);
        }
        return res;
    }

    /**
     * Update role user
     *
     * @param role - param for change user role
     * @param id - user id
     * @return true - if the update role was successful,
     *         false - if the update role was unsuccessful
     */
    public boolean updateRole(Integer role, Integer id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_ROLE)) {
            statement.setInt(1, role);
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Update status user
     *
     * @param status - param for change user status
     * @param id - user id
     * @return true - if the update status was successful,
     *         false - if the update status was unsuccessful
     */
    public boolean updateStatus(boolean status, Integer id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_STATUS)) {
            statement.setBoolean(1, status);
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Update user
     *
     * @param entity - update entity
     * @return true - if the update entity was successful,
     *         false - if the update entity was unsuccessful
     */
    public boolean update(User entity) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getLogin());
            statement.setString(4, entity.getPhoneNumber());
            statement.setBoolean(5, entity.getStatus());
            statement.setInt(6, entity.getRoleId());
            statement.setInt(7, entity.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
