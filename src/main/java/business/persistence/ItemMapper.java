package business.persistence;

import business.entities.Carport;
import business.entities.Item;
import business.entities.Request_obj;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemMapper {
    private List<Item> itemList;
    private Database database;

    public ItemMapper(Database database) {
        this.database = database;
    }


    public Item SelectItemFromDB(String name, int length) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM item WHERE `name` = ? and length = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, length);
                ResultSet rs = ps.executeQuery();
                List<Item> itemList2 = new ArrayList<>();
                if (rs.next()) {
                    int item_id = rs.getInt("item_id");
                    double price = rs.getDouble("price");
                    int width = rs.getInt("width");
                    String info = rs.getString("info");
                    Item item = new Item(item_id, info, name, price, length, width);
                    return item;
                }
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return null;
    }


    public Item ItemSelector(int item_id) throws SQLException, UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM item WHERE item_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, item_id);
                ResultSet rs = ps.executeQuery();
                List<Item> itemList2 = new ArrayList<>();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String info = rs.getString("info");
                    double price = rs.getDouble("price");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    Item item = new Item(item_id, info, name, price, length, width);
                    return item;
                }
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return null;
    }

    public void linktable(int carport_id, int item_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO carport_link (carport_id, item_id) VALUES (?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carport_id);
                ps.setInt(2, item_id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException | UserException ex) {
            throw new UserException(ex.getMessage());
        }
    }


    public List<Item> getItemList(int carport_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM carport_link WHERE carport_id = ?";


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carport_id);
                ResultSet rs = ps.executeQuery();
                List<Item> itemList = new ArrayList<>();
                while (rs.next()) {
                    int item_id = rs.getInt("item_id");
                    Item item = ItemSelector(item_id);
                    itemList.add(item);
                }
                return itemList;

            } catch (SQLException ex) {
                throw new UserException("Connection to database could not be established");
            }
        } catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
    }
}
