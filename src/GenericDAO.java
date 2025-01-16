import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenericDAO {

    protected static Connection connection = null;
    private static final String DB_URL = "jdbc:sqlite:Farmacia.db";

    public static void connect() throws SQLException {
        if (connection == null)
            connection = DriverManager.getConnection(DB_URL);
    }

    public static void disconnect() throws SQLException {
        connection.close();
        connection = null;
    }

    public static int create(Object object) throws SQLException{return -1;}
    public static Object readById(int id) throws SQLException{return null;}
    public static ArrayList<Object> readAll() throws SQLException{return null;}
    public static boolean update(Object object) throws SQLException{return true;}
    public static boolean delete(int id)  throws SQLException{return true;}
}
