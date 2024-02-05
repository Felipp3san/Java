package persistance;

import business.Models.Pessoa;
import business.Models.Veiculo;
import business.Models.VeiculoCombustao;

import java.sql.*;

public class DbAdapter {

    private static Connection connection;


    // Pessoas

    public static ResultSet inicializarHashTablePessoas() {

        String sql = "SELECT * FROM pessoa;";

        connection = ConnectionManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            System.out.println("\nNão foi possível retornar o ResultSet para inicializar a hashtable de pessoas.");
        }

        return null;
    }

    public static ResultSet buscarVeiculosPessoa(String nif) {

        String sql = "SELECT * FROM veiculo WHERE pessoa_nif=?;";

        connection = ConnectionManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(nif));
            return preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            System.out.println("\nNão foi possível retornar o ResultSet com os veículos da pessoa.");
        }

        return null;
    }

    public static void adicionarPessoa(Pessoa pessoa) {

        String sql = "INSERT INTO pessoa VALUES(?, ?, ?, ?);";

        connection = ConnectionManager.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, Integer.parseInt(pessoa.getNif()));
            preparedStatement.setString(2, pessoa.getNome());
            preparedStatement.setString(3, pessoa.getApelido());
            preparedStatement.setString(4, pessoa.getIdade());

            preparedStatement.execute();
        }
        catch (SQLException e) {
            System.out.println("\nNão foi possível adicionar a pessoa.");
        }
        finally {
            ConnectionManager.closeConnection();
            connection = null;
        }

    }

    public static boolean removerPessoa(String nif) {

        String sql = "DELETE FROM pessoa where nif=?;";

        connection = ConnectionManager.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nif);
            return preparedStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            System.out.println("\nNão foi possível remover a pessoa.");
        }
        finally {
            ConnectionManager.closeConnection();
            connection = null;
        }

        return false;
    }



    // Veiculos

    public static ResultSet inicializarHashTableVeiculos() {
        
        String sql = "SELECT * FROM veiculo;";

        connection = ConnectionManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            System.out.println("\nNão foi possível retornar o ResultSet para inicializar a hashtable de veículos.");
        }
        
        return null;
    }

    public static void adicionarVeiculo(String nif, Veiculo veiculo){

        String sql;

        connection = ConnectionManager.getConnection();

        if (veiculo instanceof VeiculoCombustao){
            sql = "INSERT INTO veiculo VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        } else {
            sql = "INSERT INTO veiculo(matricula, marca, modelo, chassi, lugares, portas, pessoa_nif, tipo_veiculo_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, veiculo.getMatricula());
            preparedStatement.setString(2, veiculo.getMarca());
            preparedStatement.setString(3, veiculo.getModelo());
            preparedStatement.setString(4, veiculo.getChassi());

            if (veiculo instanceof VeiculoCombustao){
                preparedStatement.setInt(5, ((VeiculoCombustao) veiculo).getCilindrada());
                preparedStatement.setInt(6, Integer.parseInt(veiculo.getLugares()));
                preparedStatement.setInt(7, Integer.parseInt(veiculo.getPortas()));
                preparedStatement.setString(8, nif);
                preparedStatement.setInt(9, 1);
            } else {
                preparedStatement.setInt(5, Integer.parseInt(veiculo.getLugares()));
                preparedStatement.setInt(6, Integer.parseInt(veiculo.getPortas()));
                preparedStatement.setString(7, nif);
                preparedStatement.setInt(8, 2);
            }

            preparedStatement.execute();

        }
        catch (SQLException e) {
            System.out.println("\nNão foi possível adicionar o veículo.");
        }
        finally {
            ConnectionManager.closeConnection();
            connection = null;
        }
    }

    public static boolean removerVeiculo(String matricula) {

        String sql = "DELETE FROM veiculo where matricula=?;";

        connection = ConnectionManager.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, matricula);
            return preparedStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            System.out.println("\nNão foi possível remover o veículo.");
        }
        finally {
            ConnectionManager.closeConnection();
            connection = null;
        }

        return false;
    }

    public static void removerVeiculosPessoa(String nif){

        String sql = "DELETE FROM veiculo where pessoa_nif=?;";

        connection = ConnectionManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nif);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("\nNão foi possível remover os veículos da pessoa.");
        }
        finally {
            ConnectionManager.closeConnection();
            connection = null;
        }
    }
}
