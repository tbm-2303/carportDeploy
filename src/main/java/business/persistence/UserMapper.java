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
            String sql = "INSERT INTO users (email, password, role) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRole());
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
            String sql = "SELECT user_id, role FROM user WHERE email=? AND password=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    int id = rs.getInt("user_id");
                    User user = new User(email, password, role);
                    user.setId(id);
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
                    User user = new User(email, password, role);
                    user.setNumber(telephone);
                    user.setName(name);
                    user.setAdress(adress);
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
                    User user = new User(email, password, role);
                    user.setNumber(telephone);
                    user.setName(name);
                    user.setAdress(adress);
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
