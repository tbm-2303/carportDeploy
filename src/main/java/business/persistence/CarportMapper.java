package business.persistence;

import business.entities.Carport;
import business.entities.Item;
import business.exceptions.UserException;
import business.services.ItemFacade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarportMapper {
    private Database database;
    private ItemFacade itemFacade;

    public CarportMapper(Database database) {
        this.database = database;
        itemFacade = new ItemFacade(database);
    }


    public List<Carport> getAllCarports() throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM carport";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                List<Carport> carportList = new ArrayList<>();
                while (rs.next()) {
                    String info = rs.getString("info");
                    double price = rs.getDouble("price");
                    double selling_price = rs.getDouble("selling_price");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int shed_width = rs.getInt("shed_width");
                    int shed_length = rs.getInt("shed_length");
                    int id = rs.getInt("carport_id");
                    Carport carport = new Carport(price, length, width, shed_length, shed_width, "flat", info);
                    carport.setId(id);
                    carportList.add(carport);
                }
                return carportList;
            }
            catch (SQLException ex) {
                throw new UserException("Connection to database could not be established");
            }
        } catch (SQLException e) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public Carport CreateCarportCustom(Carport carport) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO carport(price, selling_price, info, length, width, shed_length, shed_width, custom) VALUES (?,?,?,?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, carport.getPrice());
                ps.setDouble(2, carport.getSelling_price());
                ps.setString(3, carport.getInfo());
                ps.setInt(4, carport.getLength());
                ps.setInt(5, carport.getWidth());
                ps.setInt(6, carport.getShed_length());
                ps.setInt(7, carport.getShed_width());
                ps.setInt(8, 1);
                ps.executeUpdate();

                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                carport.setId(id);
                //link table
                // Linktable(carport);
                return carport;

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException e) {
            throw new UserException(e.getMessage());
        }
    }

    public void Linktable(Carport carport) throws UserException {

        for (Item item : carport.getItemList()) {
            itemFacade.Linktable(carport.getId(), item.getItem_id());
        }
    }


    public Carport getCarport(int carport_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM carport WHERE carport_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carport_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String info = rs.getString("info");
                    double price = rs.getDouble("price");
                    double profit = rs.getDouble("selling_price");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int shed_width = rs.getInt("shed_width");
                    int shed_length = rs.getInt("shed_length");
                    Carport carport = new Carport(price, length, width, shed_length, shed_width, "flat", info);
                    carport.setId(carport_id);
                    return carport;
                }
                else{
                    throw new UserException("bla");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("database error");
        }
    }

    public void removeCarportFromDB(int carport_id) {

        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM carport WHERE carport_id=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carport_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCarport(int carport_id, Carport carport) {
        try (Connection connection = database.connect()) {
            String sql = "update carport set length=?, width=?, shed_length=?, shed_width=?, price=? where carport_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, carport.getLength());
                ps.setInt(2, carport.getWidth());
                ps.setInt(3, carport.getShed_length());
                ps.setInt(4, carport.getShed_width());
                ps.setDouble(4, carport.getPrice());
                ps.setInt(5,carport_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateCarportPrice(int carport_id, double price) {
        try (Connection connection = database.connect()) {
            String sql = "update carport set price=? where carport_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setDouble(1, price);
                ps.setInt(2, carport_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Carport> getAllStandardCarports(int status) {

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM carport WHERE custom=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, status);
                ResultSet rs = ps.executeQuery();
                List<Carport> carportList = new ArrayList<>();
                while (rs.next()) {
                    String info = rs.getString("info");
                    double price = rs.getDouble("price");
                    double selling_price = rs.getDouble("selling_price");
                    int length = rs.getInt("length");
                    int width = rs.getInt("width");
                    int shed_width = rs.getInt("shed_width");
                    int shed_length = rs.getInt("shed_length");
                    int id = rs.getInt("carport_id");
                    Carport carport = new Carport(price, length, width, shed_length, shed_width, "flat", info);
                    carport.setId(id);
                    carportList.add(carport);
                }
                return carportList;
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


