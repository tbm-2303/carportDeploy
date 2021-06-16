package business.persistence;

import business.entities.*;
import business.exceptions.UserException;
import business.services.CarportFacade;
import business.services.ItemFacade;
import business.services.UserFacade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestMapper {
    private Database database;
    private CarportFacade carportFacade;
    private UserFacade userFacade;
    private ItemFacade itemFacade;

    public RequestMapper(Database database) {
        this.database = database;
        carportFacade = new CarportFacade(database);
        userFacade = new UserFacade(database);
        itemFacade = new ItemFacade(database);

    }

    public Request_obj getRequest(int request_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM request WHERE request_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, request_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int carport_id = rs.getInt("carport_id");
                    int user_id = rs.getInt("user_id");
                    String status = rs.getString("status_info");
                    User user = userFacade.getUser(user_id);
                    Carport carport = carportFacade.getCarport(carport_id);
                    Request_obj request_obj = new Request_obj(user, carport, status);
                    request_obj.setRequest_id(request_id);
                    return request_obj;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Request_obj> getAllRequest1() throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM request";


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                List<Request_obj> requestList = new ArrayList<>();
                while (rs.next()) {
                    int request_id = rs.getInt("request_id");
                    int carport_id = rs.getInt("carport_id");
                    int user_id = rs.getInt("user_id");
                    String status = rs.getString("status_info");
                    User user = userFacade.getUser(user_id);
                    Carport carport = carportFacade.getCarport(carport_id);
                    Request_obj request_obj = new Request_obj(user, carport, status);
                    request_obj.setRequest_id(request_id);
                    requestList.add(request_obj);
                }
                return requestList;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Request_obj> getAllRequest3(int user_id, String status) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM request WHERE user_id = ? and status_info = ?";


            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user_id);
                ps.setString(2, status);
                ResultSet rs = ps.executeQuery();
                List<Request_obj> requestList = new ArrayList<>();
                while (rs.next()) {
                    int request_id = rs.getInt("request_id");
                    int carport_id = rs.getInt("carport_id");
                    User user = userFacade.getUser(user_id);
                    Carport carport = carportFacade.getCarport(carport_id);
                    Request_obj request_obj = new Request_obj(user, carport, status);
                    request_obj.setRequest_id(request_id);
                    List<Item> itemlist = itemFacade.getItemList(carport_id);
                    request_obj.getCarport().setItemList(itemlist);
                    requestList.add(request_obj);
                }
                return requestList;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Request_obj> getAllRequest2(String status) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM request WHERE status_info = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ResultSet rs = ps.executeQuery();
                List<Request_obj> requestList = new ArrayList<>();
                while (rs.next()) {
                    int request_id = rs.getInt("request_id");
                    int carport_id = rs.getInt("carport_id");
                    int user_id = rs.getInt("user_id");
                    User user = userFacade.getUser(user_id);
                    Carport carport = carportFacade.getCarport(carport_id);
                    Request_obj request = new Request_obj(user, carport, status);
                    request.setRequest_id(request_id);
                    requestList.add(request);
                }
                return requestList;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public Request_obj CreateRequest(Request_obj request) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO request (carport_id, user_id, status_info) VALUES (?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, request.getCarport().getId());
                ps.setDouble(2, request.getUser().getId());
                ps.setString(3, "requested");
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                request.setRequest_id(id);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return request;
    }
    public Request_obj CreateRequest_standard(Request_obj request) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO request (carport_id, user_id, status_info) VALUES (?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, request.getCarport().getId());
                ps.setDouble(2, request.getUser().getId());
                ps.setString(3, "ordered");
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                request.setRequest_id(id);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return request;
    }

    public Double updateRequestPrice(double price, int request_id) {
        try (Connection connection = database.connect()) {
            String sql = "update request set price = ? where request_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setDouble(1, price);
                ps.setInt(2, request_id);
            }
            return price;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateRequestStatus(int request_id, String status) {

        try (Connection connection = database.connect()) {
            String sql = "update request set status_info = ? where request_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, status);
                ps.setInt(2, request_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void markAsFailed(Request_obj request_obj) {

    }

    public void removeRequestFromDB(int request_id) {
        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM request WHERE request_id=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, request_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

