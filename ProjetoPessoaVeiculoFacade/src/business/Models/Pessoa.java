package business.Models;

import exceptions.InvalidPersonDataException;
import exceptions.MoreThanThreeVehiclesException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pessoa {

    private String nome;
    private String apelido;
    private String idade;
    private String nif;
    final private ArrayList<Veiculo> veiculos = new ArrayList<>();

    public Pessoa(String nif, String nome, String apelido, String idade) {
        this.nif = nif;
        this.nome = nome;
        this.apelido = apelido;
        this.idade = idade;
    }

    public Pessoa() {
    }

    public void setNif(String nif) throws InvalidPersonDataException {
        Pattern pattern = Pattern.compile("^[0-9]{9}$");
        Matcher matcher = pattern.matcher(nif);
        boolean matchFound = matcher.find();

        if (matchFound) {
            this.nif = nif;
        } else {
            throw new InvalidPersonDataException("NIF inválido.");
        }
    }

    public void setNome(String nome) throws InvalidPersonDataException {

        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        Matcher matcher = pattern.matcher(nome);
        boolean matchFound = matcher.find();

        if (matchFound) {
            this.nome = nome;
        } else {
            throw new InvalidPersonDataException("Nome inválido.");
        }
    }

    public void setApelido(String apelido) throws InvalidPersonDataException {

        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        Matcher matcher = pattern.matcher(apelido);
        boolean matchFound = matcher.find();

        if (matchFound) {
            this.apelido = apelido;
        } else {
            throw new InvalidPersonDataException("Apelido inválido.");
        }
    }

    public void setIdade(String idade) throws InvalidPersonDataException {

        Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
        Matcher matcher = pattern.matcher(idade);
        boolean matchFound = matcher.find();

        if (matchFound) {
            this.idade = idade;
        } else {
            throw new InvalidPersonDataException("Idade inválida.");
        }
    }

    public String getNif() {
        return nif;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getIdade() {
        return this.idade;
    }

    public String getNomeCompleto() {
        return getNome() + " " + getApelido();
    }

    public ArrayList<Veiculo> getVeiculos() {
            return veiculos;
    }

    public void setVeiculo(Veiculo veiculo) throws MoreThanThreeVehiclesException {
        if (this.veiculos.size() < 3) {
            this.veiculos.add(veiculo);
        } else {
            throw new MoreThanThreeVehiclesException("O limite de veículos foi atingido.");
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.apelido);
        hash = 29 * hash + Objects.hashCode(this.idade);
        hash = 29 * hash + Objects.hashCode(this.veiculos);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.idade, other.idade)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.apelido, other.apelido)) {
            return false;
        }
        return Objects.equals(this.veiculos, other.veiculos);
    }

    @Override
    public String toString() {
        return "NIF: " + nif + "\n" +
                "Nome Completo: " + this.getNomeCompleto() + "\n" +
                "Idade: " + idade + "\n" +
                "Veiculos:\n" + veiculos + "\n" +
                "\n======================================\n";
    }

}
