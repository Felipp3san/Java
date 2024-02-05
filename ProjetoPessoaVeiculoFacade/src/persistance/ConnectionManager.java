package persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;

public class ConnectionManager {

    private static Connection connection;

    public static Connection getConnection() {

        if(connection == null) {
            try{
                FileInputStream fis = new FileInputStream("./resources/dbconfig.properties");
                PropertyResourceBundle resourceBundle = new PropertyResourceBundle(fis);
                connection = DriverManager.getConnection(
                        resourceBundle.getString("development.url"),
                        resourceBundle.getString("development.username"),
                        resourceBundle.getString("development.password"));
            } catch (SQLException | IOException e) {
                System.out.println("Erro ao conectar ao banco de dados");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("\n" + e.getMessage());
            } finally {
                connection = null;
            }
        }
    }
}
