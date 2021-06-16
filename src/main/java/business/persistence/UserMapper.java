package business.persistence;

import business.entities.Carport;
import business.exceptions.UserException;
import business.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    private Database database;

    public UserMapper(Database database) {
        this.database = database;
    }

    public void createUser(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO user (name, email, adress, telephone, role, password) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getAddress());
                ps.setString(4, user.getTelephone());
                ps.setString(5, user.getRole());
                ps.setString(6, user.getPassword());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setId(id);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public User login(String email, String password) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT user_id, name, adress, telephone, role FROM user WHERE email=? AND password=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String name = rs.getString("name");
                    String address = rs.getString("adress");
                    String telephone = rs.getString("telephone");
                    String role = rs.getString("role");
                    User user = new User(name,email,address,telephone,role,password);
                    user.setId(user_id);
                    return user;
                } else {
                    throw new UserException("Could not validate user");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public User getUser(int user_id) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `user` WHERE user_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String adress = rs.getString("adress");
                    String telephone = rs.getString("telephone");
                    String role = rs.getString("role");
                    String password = rs.getString("password");
                    User user = new User(name,email,adress,telephone,role,password);
                    user.setId(user_id);
                    return user;
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return null;
    }


    public List<User> getAllUsers() throws UserException{

        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `user`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                List<User> userList = new ArrayList<>();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String adress = rs.getString("adress");
                    String telephone = rs.getString("telephone");
                    String role = rs.getString("role");
                    String password = rs.getString("password");
                    int user_id = rs.getInt("user_id");
                    User user = new User(name,email,adress,telephone,role,password);
                    user.setId(user_id);
                    userList.add(user);
                }
                return userList;
            } catch (SQLException ex) {
                throw new UserException("Connection to database could not be established");
            }
        } catch (SQLException e) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
