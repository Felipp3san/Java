package persistance;

import business.Pessoa;
import business.VeiculoCombustao;
import business.VeiculoEletrico;

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



    // Pessoas

    public ResultSet inicializarHashTablePessoas() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pessoa;");

        return preparedStatement.executeQuery();
    }

    public ResultSet buscarVeiculosPessoa(String nif) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM veiculo WHERE pessoa_nif=?;");
        preparedStatement.setInt(1, Integer.parseInt(nif));

        return preparedStatement.executeQuery();
    }

    public void adicionarPessoa(Pessoa pessoa) throws SQLException {

        PreparedStatement preparedStatement  = connection.prepareStatement("INSERT INTO pessoa VALUES(?, ?, ?, ?);");
        preparedStatement.setInt(1, Integer.parseInt(pessoa.getNif()));
        preparedStatement.setString(2, pessoa.getNome());
        preparedStatement.setString(3, pessoa.getApelido());
        preparedStatement.setString(4, pessoa.getIdade());

        preparedStatement.execute();
    }

    public boolean removerPessoa(String nif) throws SQLException {

        PreparedStatement preparedStatement  = connection.prepareStatement("DELETE FROM pessoa where nif=?;");
        preparedStatement.setString(1, nif);

        return preparedStatement.executeUpdate() == 1;
    }



    // Veiculos

    public ResultSet inicializarHashTableVeiculos() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM veiculo;");

        return preparedStatement.executeQuery();
    }

    public void adicionarVeiculoCombustao(String nif, VeiculoCombustao veiculo) throws SQLException {

        PreparedStatement preparedStatement  = connection.prepareStatement("INSERT INTO veiculo VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);");
        preparedStatement.setString(1, veiculo.getMatricula());
        preparedStatement.setString(2, veiculo.getMarca());
        preparedStatement.setString(3, veiculo.getModelo());
        preparedStatement.setString(4, veiculo.getChassi());
        preparedStatement.setInt(5, veiculo.getCilindrada());
        preparedStatement.setInt(6, Integer.parseInt(veiculo.getLugares()));
        preparedStatement.setInt(7, Integer.parseInt(veiculo.getPortas()));
        preparedStatement.setString(8, nif);
        preparedStatement.setInt(9, 1);

        preparedStatement.execute();
    }

    public void adicionarVeiculoEletrico(String nif, VeiculoEletrico veiculo) throws SQLException {

        PreparedStatement preparedStatement  = connection.prepareStatement("INSERT INTO veiculo(matricula, marca, modelo, chassi, lugares, portas, pessoa_nif, tipo_veiculo_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
        preparedStatement.setString(1, veiculo.getMatricula());
        preparedStatement.setString(2, veiculo.getMarca());
        preparedStatement.setString(3, veiculo.getModelo());
        preparedStatement.setString(4, veiculo.getChassi());
        preparedStatement.setInt(5, Integer.parseInt(veiculo.getLugares()));
        preparedStatement.setInt(6, Integer.parseInt(veiculo.getPortas()));
        preparedStatement.setString(7, nif);
        preparedStatement.setInt(8, 2);

        preparedStatement.execute();
    }

    public boolean removerVeiculo(String matricula) throws SQLException {
        PreparedStatement preparedStatement  = connection.prepareStatement("DELETE FROM veiculo where matricula=?;");
        preparedStatement.setString(1, matricula);

        return preparedStatement.executeUpdate() == 1;
    }

    public void removerVeiculosPessoa(String nif) throws SQLException {
        PreparedStatement preparedStatement  = connection.prepareStatement("DELETE FROM veiculo where pessoa_nif=?;");
        preparedStatement.setString(1, nif);

        preparedStatement.executeUpdate();
    }
}
