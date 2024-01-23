package business;

import exceptions.EmptyHashtableException;
import exceptions.VehicleNotFoundException;

import java.util.Collection;
import java.util.Hashtable;

public class GestorVeiculos {

    static Hashtable<String, Veiculo> veiculos = new Hashtable<>();

    public void adicionarVeiculo(String matricula, Veiculo veiculo) {
        veiculos.put(matricula, veiculo);
    }

    public void removerVeiculo(String matricula) throws EmptyHashtableException, VehicleNotFoundException {
        if (!veiculos.isEmpty()) {
            if (veiculos.remove(matricula) == null) {
                throw new VehicleNotFoundException("Veiculo não encontrado.");
            }
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

    public boolean contains(Veiculo veiculo) throws VehicleNotFoundException {
        if (veiculos.contains(veiculo)) {
            return true;
        } else {
            throw new VehicleNotFoundException();
        }
    }

}
