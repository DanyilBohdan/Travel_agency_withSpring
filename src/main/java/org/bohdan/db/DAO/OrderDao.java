package org.bohdan.db.DAO;

import org.bohdan.db.DBManager;
import org.bohdan.db.Fields;
import org.bohdan.db.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDAO<Integer, Order> {

    private static final String SQL_FIND_ALL_ORDER =
            "SELECT * FROM order";
    private static final String SQL_FIND_ENTITY_BY_ID_ORDER =
            "SELECT * FROM order WHERE id=?";

    public static final String SQL_DELETE_ORDER_BY_ID =
            "DELETE FROM order WHERE id = ?";

    public static final String SQL_INSERT_ORDER =
            "insert into order (status, tour_id, user_id) values" +
                    "(?, ?, ?)";

    public static final String SQL_UPDATE_ORDER =
            "UPDATE user SET status = ?, tour_id = ?, user_id = ? WHERE id = ?";


    private Order mapOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt(Fields.ID));
        order.setStatus(rs.getString(Fields.STATUS));
        order.setTour_id(rs.getInt(Fields.TOUR_ID));
        order.setUser_id(rs.getInt(Fields.USER_ID));
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();

        try (Connection con = DBManager.getInstance().getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(SQL_FIND_ALL_ORDER)) {
            while (rs.next()) {
                orders.add(mapOrder(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order findEntityById(Integer id) {
        Order order = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_FIND_ENTITY_BY_ID_ORDER)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                order = mapOrder(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean delete(Integer id) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_DELETE_ORDER_BY_ID);) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Order entity) {
        return delete(entity.getId());
    }

    @Override
    public boolean create(Order entity) {
        boolean res = false;

        ResultSet rs = null;
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            statement.setString(k++, entity.getStatus());
            statement.setInt(k++, entity.getTour_id());
            statement.setInt(k++, entity.getUser_id());

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
            close(rs);
        }
        return res;
    }

    @Override
    public Order update(Order entity) {
        Order order = null;

        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(SQL_UPDATE_ORDER)) {
            int k = 1;
            statement.setString(k++, entity.getStatus());
            statement.setInt(k++, entity.getTour_id());
            statement.setInt(k++, entity.getUser_id());
            statement.setInt(k++, entity.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                order = mapOrder(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return order;
    }
}
