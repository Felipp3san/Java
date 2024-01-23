package persistance;

import java.sql.*;
import java.util.ResourceBundle;

public class DbAdapter {

    private ResourceBundle reader = null;
    Connection connection = null;

    public DbAdapter() throws SQLException, SQLTimeoutException {
        reader = ResourceBundle.getBundle("dbconfig.properties");
        connection = DriverManager.getConnection(reader.getString("db.url"), reader.getString("db.username"), reader.getString("db.password"));
    }

    public ResultSet buscarDados() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pessoa;");
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public void executarQuery(String nif, String nome, String apelido) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pessoa VALUES(?, ?, ?);");
        preparedStatement.setInt(1, Integer.parseInt(nif));
        preparedStatement.setString(2, nome);
        preparedStatement.setString(3, apelido);

        preparedStatement.execute();
    }
}
