package business.persistence;

import business.entities.Carport;
import business.entities.Order;
import business.entities.Request_obj;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    private Database database;

    public OrderMapper(Database database) {
        this.database = database;
    }


    public Order createOrder(Request_obj request_obj, Timestamp timestamp) throws UserException {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO orders (price, created, request_id, status_info, user_name) VALUES (?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setDouble(1, request_obj.getCarport().getPrice());
                ps.setTimestamp(2, timestamp);
                ps.setInt(3, request_obj.getRequest_id());
                ps.setString(4, request_obj.getStatus());
                ps.setString(5, request_obj.getUser().getName());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                Order order = new Order(request_obj.getCarport().getPrice(),request_obj.getRequest_id(),request_obj.getStatus(),request_obj.getUser().getName());
                order.setId(id);
                return order;
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

    public List<Order> getAllOrders(String status) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM orders WHERE status_info = ? ";


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ResultSet rs = ps.executeQuery();
                List<Order> orderList = new ArrayList<>();
                while (rs.next()) {
                    double price = rs.getDouble("price");
                    Timestamp timestamp = rs.getTimestamp("created");
                    int request_id = rs.getInt("request_id");
                    String user_name = rs.getString("user_name");
                    int order_id = rs.getInt("order_id");
                    Order order = new Order(price,request_id,status,user_name);
                    order.setTime(timestamp);
                    order.setId(order_id);
                    orderList.add(order);
                }
                return orderList;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
