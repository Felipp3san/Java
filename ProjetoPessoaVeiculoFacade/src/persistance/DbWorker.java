package persistance;

import java.sql.ResultSet;
import business.Models.Pessoa;
import business.Models.Veiculo;

public class DbWorker {

    // Pessoas

    public static ResultSet inicializarHashtablePessoas() {
        return DbAdapter.inicializarHashTablePessoas();
    }

    public static void adicionarPessoa(Pessoa pessoa) {
        DbAdapter.adicionarPessoa(pessoa);
    }

    public static boolean removerPessoa(String nif) {
        return DbAdapter.removerPessoa(nif);
    }

    public static ResultSet buscarVeiculosPessoa(String nif) {
        return DbAdapter.buscarVeiculosPessoa(nif);
    }



    // Veiculos

    public static ResultSet inicializarHashtableVeiculos() {
        return DbAdapter.inicializarHashTableVeiculos();
    }

    public static void adicionarVeiculo(String nif, Veiculo veiculo) {
        DbAdapter.adicionarVeiculo(nif, veiculo);
    }

    public static boolean removerVeiculo(String matricula) {
        return DbAdapter.removerVeiculo(matricula);
    }

    public static void removerVeiculosPessoa(String nif) {
        DbAdapter.removerVeiculosPessoa(nif);
    }

}
