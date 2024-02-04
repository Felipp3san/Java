package business;
import exceptions.InvalidVehicleDataException;

import java.util.Objects;

public class VeiculoCombustao extends Veiculo {

    private int cilindrada;

    public VeiculoCombustao() {
        super();
    }

    public VeiculoCombustao(String matricula, String marca, String modelo, String chassi, int cilindrada, String lugares, String portas) throws InvalidVehicleDataException {
        super(matricula, marca, modelo, chassi, lugares, portas);
        setCilindrada(cilindrada);
        setTaxaFixaAnual(100.00 + (getCilindrada() * 0.025));
    }

    public void setCilindrada(int cilindrada){
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    @Override
    public String toString() {
        return "\n\tMatricula: " + getMatricula() + "\n" +
                "\tMarca: " + getMarca() + "\n" +
                "\tModelo: " + getModelo() + "\n" +
                "\tChassi: " + getChassi() + "\n" +
                "\tCilindrada: " + getCilindrada() + "\n" +
                "\tLugares: " + getLugares() + "\n" +
                "\tPortas: " + getPortas() + "\n" +
                "\tTaxa Fixa Anual: " + getTaxaFixaAnual() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeiculoCombustao veiculo = (VeiculoCombustao) o;
        return Double.compare(getTaxaFixaAnual(), veiculo.getTaxaFixaAnual()) == 0 && Objects.equals(getMatricula(), veiculo.getMatricula()) && Objects.equals(getMarca(), veiculo.getMarca()) && Objects.equals(getModelo(), veiculo.getModelo()) && Objects.equals(getChassi(), veiculo.getChassi()) && Objects.equals(getCilindrada(), veiculo.getCilindrada()) && Objects.equals(getLugares(), veiculo.getLugares()) && Objects.equals(getPortas(), veiculo.getPortas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatricula(), getMarca(), getModelo(), getChassi(), getCilindrada(), getLugares(), getPortas(), getTaxaFixaAnual());
    }
}
