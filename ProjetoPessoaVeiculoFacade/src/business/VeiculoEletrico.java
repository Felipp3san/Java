package business;

import java.util.Objects;

public class VeiculoEletrico extends Veiculo {

    public VeiculoEletrico() {
        super();
    }

    public VeiculoEletrico(String matricula, String marca, String modelo, String chassi, String lugares, String portas) {
        super(matricula, marca, modelo, chassi, lugares, portas);
        setTaxaFixaAnual(25.0);
    }


    @Override
    public void setTaxaFixaAnual() {
        super.setTaxaFixaAnual(25.0);
    }

    @Override
    public String toString() {
        return "\n\tMatricula: " + getMatricula() + "\n" +
                "\tMarca: " + getMarca() + "\n" +
                "\tModelo: " + getModelo() + "\n" +
                "\tChassi: " + getChassi() + "\n" +
                "\tLugares: " + getLugares() + "\n" +
                "\tPortas: " + getPortas() + "\n" +
                "\tTaxa Fixa Anual: " + getTaxaFixaAnual() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeiculoEletrico veiculo = (VeiculoEletrico) o;
        return Double.compare(getTaxaFixaAnual(), veiculo.getTaxaFixaAnual()) == 0 && Objects.equals(getMatricula(), veiculo.getMatricula()) && Objects.equals(getMarca(), veiculo.getMarca()) && Objects.equals(getModelo(), veiculo.getModelo()) && Objects.equals(getChassi(), veiculo.getChassi()) && Objects.equals(getLugares(), veiculo.getLugares()) && Objects.equals(getPortas(), veiculo.getPortas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatricula(), getMarca(), getModelo(), getChassi(), getLugares(), getPortas(), getTaxaFixaAnual());
    }
}
