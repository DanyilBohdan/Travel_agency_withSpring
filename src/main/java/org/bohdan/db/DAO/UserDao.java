package org.bohdan.db.DAO;

import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {


    private static final String SQL_FIND_ALL_USER =
            "SELECT * FROM user";

    private static final String SQL_FIND_ENTITY_BY_ID_USER =
            "SELECT * FROM user WHERE id = ?";

    public static final String SQL_DELETE_USER_BY_ID =
            "DELETE FROM user WHERE id = ?";

    public static final String SQL_INSERT_USER =
            "insert into user (username, password, email, phone_number, status, role_id) values " +
                    "(?, ?, ?, ?, ?, ?)";

    public static final String SQL_UPDATE_USER =
            "UPDATE user SET username = ?, password = ?, email = ?, phone_number = ?, status = ?, role_id = ? WHERE id = ?";

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ID));
        user.setUsername(rs.getString(Fields.USERNAME));
        user.setPassword(rs.getString(Fields.PASSWORD));
        user.setEmail(rs.getString(Fields.EMAIL));
        user.setPhone_number(rs.getString(Fields.PHONE_NUMBER));
        user.setStatus(rs.getBoolean(Fields.STATUS));
        user.setRole_id(rs.getInt(Fields.ROLE_ID));
        return user;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_USER)) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException ex) {

        }
        return users;
    }

    public User findEntityById(Integer id) {
        User user = null;
        try (Connection con = DBManager.getInstance().getConnection();
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

    public boolean delete(Integer id) {
        try (Connection con = DBManager.getInstance().getConnection();
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

    public boolean create(User entity) {
        boolean res = false;

        ResultSet rs = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            statement.setString(k++, entity.getUsername());
            statement.setString(k++, entity.getPassword());
            statement.setString(k++, entity.getEmail());
            statement.setString(k++, entity.getPhone_number());
            statement.setBoolean(k++, entity.getStatus());
            statement.setInt(k++, entity.getRole_id());

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

    public User update(User entity) {
        User user = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPhone_number());
            statement.setBoolean(5, entity.getStatus());
            statement.setInt(6, entity.getRole_id());
            statement.setInt(7, entity.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = mapUser(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
