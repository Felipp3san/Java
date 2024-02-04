package persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import business.Pessoa;
import business.VeiculoCombustao;
import business.VeiculoEletrico;

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

    public static void adicionarVeiculoCombustao(String nif, VeiculoCombustao veiculo) throws SQLException {
        dbAdapter.adicionarVeiculoCombustao(nif, veiculo);
    }

    public static void adicionarVeiculoEletrico(String nif, VeiculoEletrico veiculo) throws SQLException {
        dbAdapter.adicionarVeiculoEletrico(nif, veiculo);
    }

    public static boolean removerVeiculo(String matricula) throws SQLException {
        return dbAdapter.removerVeiculo(matricula);
    }

    public static void removerVeiculosPessoa(String nif) throws SQLException {
        dbAdapter.removerVeiculosPessoa(nif);
    }

}
