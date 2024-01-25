package business;

import exceptions.InvalidVehicleDataException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Veiculo {

    private String matricula;
    private String marca;
    private String modelo;
    private String chassi;
    private String cilindrada;
    private String lugares;
    private String portas;

    public Veiculo() {
    }

    public Veiculo(String matricula, String marca, String modelo, String chassi, String cilindrada, String lugares,
            String portas) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.chassi = chassi;
        this.cilindrada = cilindrada;
        this.lugares = lugares;
        this.portas = portas;
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

    public String getCilindrada() {
        return cilindrada;
    }

    public String getLugares() {
        return lugares;
    }

    public String getPortas() {
        return portas;
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

    public void setCilindrada(String cilindrada) throws InvalidVehicleDataException {

        Pattern pattern = Pattern.compile("^[0-9]{1,6}$");
        Matcher matcher = pattern.matcher(cilindrada);
        boolean matchFound = matcher.find();

        if (matchFound) {
            this.cilindrada = cilindrada;
        } else {
            throw new InvalidVehicleDataException("Cilindrada inválida.");
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

    @Override
    public String toString() {
        return "\n\tMatricula: " + matricula + "\n" +
                "\tMarca: " + marca + "\n" +
                "\tModelo: " + modelo + "\n" +
                "\tChassi: " + chassi + "\n" +
                "\tCilindrada: " + cilindrada + "\n" +
                "\tLugares: " + lugares + "\n" +
                "\tPortas: " + portas + "\n";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.matricula);
        hash = 29 * hash + Objects.hashCode(this.marca);
        hash = 29 * hash + Objects.hashCode(this.modelo);
        hash = 29 * hash + Objects.hashCode(this.chassi);
        hash = 29 * hash + Objects.hashCode(this.cilindrada);
        hash = 29 * hash + Objects.hashCode(this.lugares);
        hash = 29 * hash + Objects.hashCode(this.portas);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.cilindrada, other.cilindrada)) {
            return false;
        }
        if (!Objects.equals(this.lugares, other.lugares)) {
            return false;
        }
        if (!Objects.equals(this.portas, other.portas)) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        return Objects.equals(this.chassi, other.chassi);
    }
}
