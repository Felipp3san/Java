package persistance;

import business.Veiculo;
import exceptions.EmptyDatabaseTableException;

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
                resourceBundle.getString("development.url"),
                resourceBundle.getString("development.username"),
                resourceBundle.getString("development.password"));
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

    public ResultSet inicializarHashTable() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pessoa;");

        return preparedStatement.executeQuery();
    }

    public ResultSet buscarDados(String tabela) throws SQLException, EmptyDatabaseTableException {

        PreparedStatement preparedStatement = null;

        if (tabela.equals("pessoa"))
            preparedStatement = connection.prepareStatement("SELECT * FROM pessoa;");
        else if (tabela.equals("veiculo"))
            preparedStatement = connection.prepareStatement("SELECT * FROM veiculo;");

        if (contagemTabela(tabela) > 0 && preparedStatement != null)
            return preparedStatement.executeQuery();
        else
            throw new EmptyDatabaseTableException("Tabela vazia.");
    }

    public void executarQuery(String nif, String nome, String apelido, String idade) throws SQLException {

        PreparedStatement preparedStatement  = connection.prepareStatement("INSERT INTO pessoa VALUES(?, ?, ?, ?);");
        preparedStatement.setInt(1, Integer.parseInt(nif));
        preparedStatement.setString(2, nome);
        preparedStatement.setString(3, apelido);
        preparedStatement.setString(4, idade);

        preparedStatement.execute();

    }

    public boolean executarQuery(String nif) throws SQLException {

        PreparedStatement preparedStatement  = connection.prepareStatement("DELETE FROM pessoa where nif=?;");
        preparedStatement.setString(1, nif);

        return preparedStatement.executeUpdate() == 1;
    }

    public void adicionarVeiculo(Veiculo veiculo, String nif) throws SQLException {

        PreparedStatement preparedStatement  = connection.prepareStatement("INSERT INTO veiculo VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
        preparedStatement.setString(1, veiculo.getMatricula());
        preparedStatement.setString(2, veiculo.getMarca());
        preparedStatement.setString(3, veiculo.getModelo());
        preparedStatement.setString(4, veiculo.getChassi());
        preparedStatement.setInt(5, Integer.parseInt(veiculo.getCilindrada()));
        preparedStatement.setInt(6, Integer.parseInt(veiculo.getLugares()));
        preparedStatement.setInt(7, Integer.parseInt(veiculo.getPortas()));
        preparedStatement.setString(8, nif);

        preparedStatement.execute();
    }

    public static int contagemTabela(String tabela) throws SQLException {

        PreparedStatement preparedStatement = null;

        if (tabela.equals("pessoa"))
            preparedStatement  = connection.prepareStatement("SELECT COUNT(*) FROM pessoa;");
        else if (tabela.equals("veiculo"))
            preparedStatement  = connection.prepareStatement("SELECT COUNT(*) FROM veiculo;");

        if(preparedStatement != null){
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        else {
            return 0;
        }
    }
}
