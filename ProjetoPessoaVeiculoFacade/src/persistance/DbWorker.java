package persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

public class DbWorker {

    DbAdapter dbAdapter = null;

    public DbWorker() throws SQLException {
        dbAdapter = new DbAdapter();
    }

    public ResultSet buscarPessoas() throws SQLException, SQLTimeoutException {
        return dbAdapter.buscarDados();
    }

    public void adicionarPessoa(String nif, String nome, String apelido) throws SQLException {
        dbAdapter.executarQuery(nif, nome, apelido);
    }
}
