package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteHashtable {
    public static void main(String[] args) {

        String server = "192.168.64.8";
        String username = "forasteiro";
        String password = "123";
        String dbname = "clinica_V1_1";

        String url = "jdbc:mysql://" + server + ":3306/" + dbname;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conectado!");

            // PreparedStatement preparedStatement = connection.prepareStatement("SELECT *
            // FROM utente;");
            // ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
