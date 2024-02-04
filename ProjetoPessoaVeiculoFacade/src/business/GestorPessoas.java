package business;

import exceptions.*;
import persistance.DbWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Hashtable;

public class GestorPessoas {

    Hashtable<String, Pessoa> pessoas = new Hashtable<>();

    public GestorPessoas() throws SQLException {

        ResultSet pessoasSet = DbWorker.inicializarHashtablePessoas();

        while(pessoasSet.next()){
            Pessoa pessoa = new Pessoa(
                    pessoasSet.getString(1),
                    pessoasSet.getString(2),
                    pessoasSet.getString(3),
                    pessoasSet.getString(4)
                    );

            ResultSet veiculosSet = DbWorker.buscarVeiculosPessoa(pessoasSet.getString(1));

            while(veiculosSet.next()){

                if (veiculosSet.getInt(9) == 1) {
                    VeiculoCombustao veiculo = new VeiculoCombustao(
                            veiculosSet.getString(1),
                            veiculosSet.getString(2),
                            veiculosSet.getString(3),
                            veiculosSet.getString(4),
                            veiculosSet.getInt(5),
                            veiculosSet.getString(6),
                            veiculosSet.getString(7)
                    );

                    try {
                        pessoa.setVeiculoCombustao(veiculo);
                    } catch (MoreThanThreeVehiclesException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    VeiculoEletrico veiculo = new VeiculoEletrico(
                            veiculosSet.getString(1),
                            veiculosSet.getString(2),
                            veiculosSet.getString(3),
                            veiculosSet.getString(4),
                            veiculosSet.getString(6),
                            veiculosSet.getString(7)
                    );
                    try {
                        pessoa.setVeiculoEletrico(veiculo);
                    } catch (MoreThanThreeVehiclesException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            pessoas.put(pessoasSet.getString(1), pessoa);
        }
    }

    public void adicionarPessoa(Pessoa pessoa) throws SQLException {

        DbWorker.adicionarPessoa(pessoa);
    }

    public void removerPessoa(String nif) throws EmptyHashtableException, SQLException {

        if (!pessoas.isEmpty()) {
            if (!DbWorker.removerPessoa(nif))
                throw new SQLException("Pessoa não encontrada na base de dados.");
        }
        else {
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

    public Collection<Pessoa> getPessoas() throws EmptyHashtableException {

        if (!pessoas.isEmpty()) {
            return pessoas.values();
        } else {
            throw new EmptyHashtableException("A lista de pessoas está vazia.");
        }
    }
}
