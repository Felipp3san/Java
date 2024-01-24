package persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbWorker {

    private static final DbAdapter dbAdapter = DbAdapter.getInstance();

    public static ResultSet buscarPessoas() throws SQLException {
        return dbAdapter.buscarDados();
    }

    public static void adicionarPessoa(String nif, String nome, String apelido, String idade) throws SQLException {
        dbAdapter.executarQuery(nif, nome, apelido, idade);
    }

    public static boolean removerPessoa(String nif) throws SQLException {
        return dbAdapter.executarQuery(nif);
    }
}
