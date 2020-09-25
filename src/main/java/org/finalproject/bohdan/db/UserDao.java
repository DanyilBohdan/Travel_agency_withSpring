package org.finalproject.bohdan.db;

import org.finalproject.bohdan.db.entity.TypeTour;
import org.finalproject.bohdan.db.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String SQL_FIND_ALL_USER =
            "SELECT * FROM user";

    public List<User> findAllUser() {
        List<User> users = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_USER)) {

            while (rs.next()) {
                users.add(mapTypeTour(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    private User mapTypeTour(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ID));
        user.setUsername(rs.getString(Fields.USERNAME));
        user.setPassword(rs.getString(Fields.PASSWORD));
        user.setEmail(rs.getString(Fields.EMAIL));
        user.setStatus(rs.getString(Fields.STATUS));
        user.setRole_id(rs.getInt(Fields.ROLE_ID));
        return user;
    }

}
