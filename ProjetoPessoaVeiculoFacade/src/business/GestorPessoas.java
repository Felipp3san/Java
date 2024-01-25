package business;

import exceptions.*;
import persistance.DbWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Hashtable;

public class GestorPessoas {

    static Hashtable<String, Pessoa> pessoas = new Hashtable<>();

    public GestorPessoas() throws SQLException {

        ResultSet resultSet = DbWorker.inicializarHashtable();

        while(resultSet.next()){
            Pessoa pessoa = new Pessoa(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
                    );
            pessoas.put(resultSet.getString(1), pessoa);
        }
    }

    public void adicionarPessoa(Pessoa pessoa) throws SQLException {

        String nif = pessoa.getNif();
        String nome = pessoa.getNome();
        String apelido = pessoa.getApelido();
        String idade = pessoa.getIdade();

        DbWorker.adicionarPessoa(nif, nome, apelido, idade);

        pessoas.put(pessoa.getNif(), pessoa);
    }

    public void removerPessoa(String nif) throws EmptyHashtableException, PersonNotFoundException, SQLException {

        if (!pessoas.isEmpty()) {
            if(!DbWorker.removerPessoa(nif)){
                throw new SQLException("Pessoa não encontrada na base de dados.");
            }
            else {
                if (pessoas.remove(nif) == null) {
                    throw new PersonNotFoundException("Pessoa não encontrada.");
                }
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

    public void associarVeiculo(String nif, Veiculo veiculo) throws MoreThanThreeVehiclesException {
        pessoas.get(nif).setVeiculo(veiculo);
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

    public ResultSet getPessoasBD() throws SQLException, EmptyDatabaseTableException {
        return DbWorker.buscarPessoas();
    }
}
