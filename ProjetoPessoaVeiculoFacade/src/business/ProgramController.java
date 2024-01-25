package business;

import exceptions.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class ProgramController {

    GestorPessoas gestorPessoas;
    GestorVeiculos gestorVeiculos;

    public ProgramController() throws SQLException, IOException, EmptyDatabaseTableException {
        gestorPessoas = new GestorPessoas();
        gestorVeiculos = new GestorVeiculos();
    }

    public void adicionarPessoa(Pessoa pessoa) throws SQLException {

        gestorPessoas.adicionarPessoa(pessoa);
    }

    public void removerPessoa(String nif) throws EmptyHashtableException, PersonNotFoundException, SQLException {
        gestorPessoas.removerPessoa(nif);
    }

    public Pessoa getPessoa(String nif) throws EmptyHashtableException, PersonNotFoundException {
        return gestorPessoas.getPessoa(nif);
    }

    public Collection<Pessoa> getPessoas() throws EmptyHashtableException {
        return gestorPessoas.getPessoas();
    }

    public void adicionarVeiculo(Veiculo veiculo, String nif)
            throws MoreThanThreeVehiclesException, SQLException {
        gestorPessoas.associarVeiculo(nif, veiculo);
        gestorVeiculos.adicionarVeiculo(veiculo, nif);
    }

    public void removerVeiculo(String matricula, Pessoa pessoa)
            throws EmptyHashtableException, VehicleNotFoundException {
        gestorPessoas.desassociarVeiculo(matricula, pessoa);
        gestorVeiculos.removerVeiculo(matricula);
    }

    public Veiculo getVeiculo(String matricula) throws EmptyHashtableException, VehicleNotFoundException {
        return gestorVeiculos.getVeiculo(matricula);
    }

    public Collection<Veiculo> getVeiculos() throws EmptyHashtableException {
        return gestorVeiculos.getVeiculos();
    }

    public ResultSet buscarPessoasBD() throws SQLException, EmptyDatabaseTableException {
        return gestorPessoas.getPessoasBD();
    }

    public ResultSet buscarVeiculosBD() throws SQLException, EmptyDatabaseTableException {
        return gestorVeiculos.getVeiculosBD();
    }
}
