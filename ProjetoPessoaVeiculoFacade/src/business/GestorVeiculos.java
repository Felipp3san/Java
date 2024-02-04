package business;

import exceptions.EmptyHashtableException;
import exceptions.VehicleNotFoundException;
import persistance.DbWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Hashtable;

public class GestorVeiculos {

    Hashtable<String, Veiculo> veiculos = new Hashtable<>();

    public GestorVeiculos() throws SQLException {

        ResultSet veiculosSet = DbWorker.inicializarHashtableVeiculos();

        while(veiculosSet.next()){
            Veiculo veiculo = new Veiculo(
                    veiculosSet.getString(1),
                    veiculosSet.getString(2),
                    veiculosSet.getString(3),
                    veiculosSet.getString(4),
                    veiculosSet.getString(5),
                    veiculosSet.getString(6),
                    veiculosSet.getString(7)
                    );
            veiculos.put(veiculosSet.getString(1), veiculo);
        }
    }

    public void adicionarVeiculo(Veiculo veiculo, String nif) throws SQLException {

        DbWorker.adicionarVeiculo(veiculo, nif);
        new GestorVeiculos();
    }

    public void removerVeiculo(String matricula) throws EmptyHashtableException, SQLException {

        if (!veiculos.isEmpty()) {
            if(!DbWorker.removerVeiculo(matricula))
                throw new SQLException("Veiculo não encontrada na base de dados.");
        } else {
            throw new EmptyHashtableException("A lista de veículos está vazia.");
        }
    }

    public Veiculo getVeiculo(String matricula) throws EmptyHashtableException, VehicleNotFoundException {

        if (!veiculos.isEmpty()) {
            if (veiculos.get(matricula) == null) {
                throw new VehicleNotFoundException("Veiculo não encontrado.");
            } else {
                return veiculos.get(matricula);
            }
        } else {
            throw new EmptyHashtableException("A lista de veiculos está vazia.");
        }
    }

    public Collection<Veiculo> getVeiculos() throws EmptyHashtableException {

        if (!veiculos.isEmpty()) {
            return veiculos.values();
        } else {
            throw new EmptyHashtableException("A lista de veiculos está vazia.");
        }
    }

    public void removerVeiculosPessoa(String nif) throws SQLException {
        DbWorker.removerVeiculosPessoa(nif);
    }
}
