package persistance;

import exceptions.EmptyDatabaseTableException;

import java.sql.ResultSet;
import java.sql.SQLException;
import business.Veiculo;

public class DbWorker {

    private static final DbAdapter dbAdapter = DbAdapter.getInstance();

    public static ResultSet inicializarHashtable() throws SQLException {
        return dbAdapter.inicializarHashTable();
    }

    public static ResultSet buscarPessoas() throws SQLException, EmptyDatabaseTableException {
        return dbAdapter.buscarDados("pessoa");
    }

    public static void adicionarPessoa(String nif, String nome, String apelido, String idade) throws SQLException {
        dbAdapter.executarQuery(nif, nome, apelido, idade);
    }

    public static boolean removerPessoa(String nif) throws SQLException {
        return dbAdapter.executarQuery(nif);
    }

    public static void adicionarVeiculo(Veiculo veiculo, String nif) throws SQLException {
        dbAdapter.adicionarVeiculo(veiculo, nif);
    }

    public static ResultSet buscarVeiculos() throws SQLException, EmptyDatabaseTableException {
        return dbAdapter.buscarDados("veiculo");
    }
}
