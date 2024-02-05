package business.Models;

import exceptions.InvalidVehicleDataException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Veiculo {

    private String matricula;
    private String marca;
    private String modelo;
    private String chassi;
    private String lugares;
    private String portas;
    private double taxaFixaAnual;

    public Veiculo() {
        this.matricula = "";
        this.marca = "";
        this.modelo = "";
        this.chassi = "";
        this.lugares = "";
        this.portas = "";
        this.taxaFixaAnual = 0.0;
    }

    public Veiculo(String matricula, String marca, String modelo, String chassi, String lugares, String portas) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.chassi = chassi;
        this.lugares = lugares;
        this.portas = portas;
        this.taxaFixaAnual = 0.0;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getChassi() {
        return chassi;
    }

    public String getLugares() {
        return lugares;
    }

    public String getPortas() {
        return portas;
    }

    public double getTaxaFixaAnual() {
        return taxaFixaAnual;
    }

    public void setMatricula(String matricula) throws InvalidVehicleDataException {

        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{6}$");
        Matcher matcher = pattern.matcher(matricula);
        boolean matchFound = matcher.find();

        if (matchFound) {
            this.matricula = matricula;
        } else {
            throw new InvalidVehicleDataException("Matricula inválida.");
        }
    }

    public void setMarca(String marca) throws InvalidVehicleDataException {

        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(marca);
        boolean matchFound = matcher.find();

        if (matchFound) {
            this.marca = marca;
        } else {
            throw new InvalidVehicleDataException("Marca inválida.");
        }
    }

    public void setModelo(String modelo) throws InvalidVehicleDataException {

        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(modelo);
        boolean matchFound = matcher.find();

        if (matchFound) {
            this.modelo = modelo;
        } else {
            throw new InvalidVehicleDataException("Modelo inválido.");
        }
    }

    public void setChassi(String chassi) throws InvalidVehicleDataException {

        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{12}$");
        Matcher matcher = pattern.matcher(chassi);
        boolean matchFound = matcher.find();

        if (matchFound) {
            this.chassi = chassi;
        } else {
            throw new InvalidVehicleDataException("Número de chassi inválido.");
        }
    }

    public void setLugares(String lugares) throws InvalidVehicleDataException {

        Pattern pattern = Pattern.compile("^[0-9]{1,2}$");
        Matcher matcher = pattern.matcher(lugares);
        boolean matchFound = matcher.find();

        if (matchFound) {
            this.lugares = lugares;
        } else {
            throw new InvalidVehicleDataException("Quantidade de lugares inválida.");
        }
    }

    public void setPortas(String portas) throws InvalidVehicleDataException {

        Pattern pattern = Pattern.compile("^[0-9]{1,2}$");
        Matcher matcher = pattern.matcher(portas);
        boolean matchFound = matcher.find();

        if (matchFound) {
            this.portas = portas;
        } else {
            throw new InvalidVehicleDataException("Quantidade de portas inválida.");
        }
    }

    public void setTaxaFixaAnual(double taxaFixaAnual) {
        this.taxaFixaAnual = taxaFixaAnual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Double.compare(taxaFixaAnual, veiculo.taxaFixaAnual) == 0 && Objects.equals(matricula, veiculo.matricula) && Objects.equals(marca, veiculo.marca) && Objects.equals(modelo, veiculo.modelo) && Objects.equals(chassi, veiculo.chassi) && Objects.equals(lugares, veiculo.lugares) && Objects.equals(portas, veiculo.portas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, marca, modelo, chassi, lugares, portas, taxaFixaAnual);
    }
}
