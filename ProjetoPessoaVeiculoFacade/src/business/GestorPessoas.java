package business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.Collection;
import java.util.Hashtable;
import exceptions.EmptyHashtableException;
import exceptions.MoreThanThreeVehiclesException;
import exceptions.PersonNotFoundException;
import exceptions.VehicleNotFoundException;
import persistance.DbWorker;

public class GestorPessoas {

    DbWorker dbWorker = null;
    static Hashtable<String, Pessoa> pessoas = new Hashtable<>();

    public GestorPessoas() throws SQLException {
        dbWorker = new DbWorker();
    }

    public void adicionarPessoa(Pessoa pessoa) throws SQLException {

        String nif = pessoa.getNif();
        String nome = pessoa.getNome();
        String apelido = pessoa.getApelido();

        dbWorker.adicionarPessoa(nif, nome, apelido);
        pessoas.put(pessoa.getNif(), pessoa);
    }

    public void removerPessoa(String nif) throws EmptyHashtableException, PersonNotFoundException {

        if (!pessoas.isEmpty()) {
            if (pessoas.remove(nif) == null) {
                throw new PersonNotFoundException("Pessoa não encontrada.");
            }
        } else {
            throw new EmptyHashtableException("A lista de pessoas está vazia.");
        }
    }

    public Collection<Pessoa> getPessoas() throws EmptyHashtableException {

        if (!pessoas.isEmpty()) {
            return pessoas.values();
        } else {
            throw new EmptyHashtableException("A lista de pessoas está vazia.");
        }
    }

    public Pessoa getPessoa(String nif) throws EmptyHashtableException, PersonNotFoundException {

        if (!pessoas.isEmpty()) {
            if (pessoas.get(nif) == null) {
                throw new PersonNotFoundException("Pessoa não encontrada.");
            } else {
                return pessoas.get(nif);
            }
        } else {
            throw new EmptyHashtableException("A lista de pessoas está vazia.");
        }
    }

    public void associarVeiculo(Pessoa pessoa, Veiculo veiculo) throws MoreThanThreeVehiclesException {
        pessoa.setVeiculo(veiculo);
    }

    public void desassociarVeiculo(String matricula, Pessoa pessoa) throws VehicleNotFoundException {

        int indexVeiculo = -1;

        int i = 0;
        for (Veiculo veiculo : pessoa.getVeiculos()) {
            if (veiculo.getMatricula().equals(matricula)) {
                indexVeiculo = i;
            }
            i++;
        }

        if (indexVeiculo != -1) {
            pessoa.removerVeiculo(indexVeiculo);
        } else {
            throw new VehicleNotFoundException("Veículo não encontrado.");
        }
    }

    public boolean contains(Pessoa pessoa) throws PersonNotFoundException {
        if (pessoas.contains(pessoa)) {
            return true;
        } else {
            throw new PersonNotFoundException();
        }
    }

    public ResultSet getPessoasBD() throws SQLException, SQLTimeoutException {
        return dbWorker.buscarPessoas();
    }
}
