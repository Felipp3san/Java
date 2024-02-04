package persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import business.Veiculo;
import business.Pessoa;

public class DbWorker {

    private static final DbAdapter dbAdapter = DbAdapter.getInstance();


    // Pessoas

    public static ResultSet inicializarHashtablePessoas() throws SQLException {
        return dbAdapter.inicializarHashTablePessoas();
    }

    public static void adicionarPessoa(Pessoa pessoa) throws SQLException {
        dbAdapter.adicionarPessoa(pessoa);
    }

    public static boolean removerPessoa(String nif) throws SQLException {
        return dbAdapter.removerPessoa(nif);
    }

    public static ResultSet buscarVeiculosPessoa(String nif) throws SQLException {
        return dbAdapter.buscarVeiculosPessoa(nif);
    }



    // Veiculos

    public static ResultSet inicializarHashtableVeiculos() throws SQLException {
        return dbAdapter.inicializarHashTableVeiculos();
    }

    public static void adicionarVeiculo(Veiculo veiculo, String nif) throws SQLException {
        dbAdapter.adicionarVeiculo(veiculo, nif);
    }

    public static boolean removerVeiculo(String matricula) throws SQLException {
        return dbAdapter.removerVeiculo(matricula);
    }

    public static void removerVeiculosPessoa(String nif) throws SQLException {
        dbAdapter.removerVeiculosPessoa(nif);
    }

}
