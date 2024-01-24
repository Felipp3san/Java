package persistance;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.PropertyResourceBundle;

public class DbAdapter {

    private static DbAdapter instance;
    static Connection connection;

    private DbAdapter() throws SQLException, IOException {
        FileInputStream fis = new FileInputStream("./resources/dbconfig.properties");
        PropertyResourceBundle resourceBundle = new PropertyResourceBundle(fis);
        connection = DriverManager.getConnection(
                resourceBundle.getString("db.url"),
                resourceBundle.getString("db.username"),
                resourceBundle.getString("db.password"));
    }

    public static DbAdapter getInstance() {
        if (instance == null) {
            try {
                instance = new DbAdapter();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public ResultSet buscarDados() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pessoa;");

        return preparedStatement.executeQuery();
    }

    public void executarQuery(String nif, String nome, String apelido, String idade) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pessoa VALUES(?, ?, ?, ?);");
        preparedStatement.setInt(1, Integer.parseInt(nif));
        preparedStatement.setString(2, nome);
        preparedStatement.setString(3, apelido);
        preparedStatement.setString(4, idade);

        preparedStatement.execute();

    }

    public boolean executarQuery(String nif) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pessoa where nif=?;");
        preparedStatement.setString(1, nif);

        return preparedStatement.executeUpdate() > 0;
    }

}
