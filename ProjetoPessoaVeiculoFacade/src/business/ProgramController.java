package business;

import exceptions.EmptyHashtableException;
import exceptions.MoreThanThreeVehiclesException;
import exceptions.PersonNotFoundException;
import exceptions.VehicleNotFoundException;
import persistance.DbAdapter;
import persistance.DbWorker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class ProgramController {

    GestorPessoas gestorPessoas;
    GestorVeiculos gestorVeiculos = new GestorVeiculos();

    public ProgramController() throws SQLException, IOException {
        gestorPessoas = new GestorPessoas();
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

    public boolean contains(Pessoa pessoa) throws PersonNotFoundException {
        return gestorPessoas.contains(pessoa);
    }

    public void adicionarVeiculo(String matricula, Veiculo veiculo, Pessoa pessoa)
            throws MoreThanThreeVehiclesException {
        gestorPessoas.associarVeiculo(pessoa, veiculo);
        gestorVeiculos.adicionarVeiculo(matricula, veiculo);
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

    public boolean contains(Veiculo veiculo) throws VehicleNotFoundException {
        return gestorVeiculos.contains(veiculo);
    }

    public ResultSet buscarPessoasBD() throws SQLException {
        return gestorPessoas.getPessoasBD();
    }
}
