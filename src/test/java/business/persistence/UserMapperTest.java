package business.persistence;

import business.entities.User;
import business.exceptions.UserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final static String DATABASE = "carportdb";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "root";
    private final static String PASSWORD = "Timmy2008";
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static UserMapper userMapper;

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            userMapper = new UserMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }

    @BeforeEach
    public void setUp() {

        // reset test database
        try (Statement stmt = database.connect().createStatement()) {
            stmt.execute("drop table if exists user");
            stmt.execute("create table " + TESTDATABASE + ".user LIKE " + DATABASE + ".user;");
            stmt.execute(
                    "insert into user values " +
                            "(10,'test1','test1@hotmail.com','testby','123','customer','password1'), " +
                            "(20,'test2','test2@hotmail.com','testby','123','customer','password2'), " +
                            "(30,'test3','test3@hotmail.com','testby','123','customer','password3')");
        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(database);
    }

    @Test
    public void testLogin01() throws UserException {
        // Can we log in
        User user = userMapper.login("test1@hotmail.com", "password1");
        assertTrue(user != null);
    }

    @Test
    public void testLogin02() throws UserException {
        // We should get an exception if we use the wrong password
        assertThrows(UserException.class, () ->
        {
            User user = userMapper.login("test1@hotmail.com", "wrong");
        });

    }

    @Test
    public void testLogin03() throws UserException {
        // Jens is supposed to be a customer. this does'nt actually test anything.
        User user = userMapper.login("test1@hotmail.com", "password1");
        assertEquals("customer", user.getRole());
    }

    @Test
    public void testCreateUser01() throws UserException {
        // Can we create a new user - Notice, if login fails, this will fail
        // but so would login01, so this is OK
        User original = new User("king@kong.com", "test", "test", "123", "customer", "konge");
        userMapper.createUser(original);
        User retrieved = userMapper.login("king@kong.com", "konge");
        assertEquals("customer", retrieved.getRole());

    }
}
