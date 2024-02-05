package presentation;

import business.*;
import business.Models.Pessoa;
import business.Models.Veiculo;
import business.Models.VeiculoCombustao;
import business.Models.VeiculoEletrico;
import exceptions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class StartConsole {

    static Scanner scanner = new Scanner(System.in);
    static String op = "";
    static ProgramController programController = null;

    public static void main(String[] args) {

        /*
         * Tenta estabelecer conexão com a base de bases,
         * caso não tenha sucesso, encerra o programa.
         */
        try {
            programController = new ProgramController();
        } catch (SQLException | IOException e) {
            System.out.println("\n" + e.getMessage());
            op = "0";
        }

        while (!op.equals("0")) {

            menu();
            op = scanner.nextLine();
            limparConsole();

            switch (op) {
                case "0":
                    System.out.println("\nPrograma finalizado.");
                    break;
                case "1":
                    opcao1();
                    break;
                case "2":
                    opcao2();
                    break;
                case "3":
                    opcao3();
                    break;
                case "4":
                    opcao4();
                    break;
                case "5":
                    opcao5();
                    break;
                case "6":
                    opcao6();
                    break;
                case "7":
                    opcao7();
                    break;
                case "8":
                    opcao8();
                    break;
                default:
                    System.out.println("\nOpção inválida.");
            }
            aguardarInput();
        }
        scanner.close();
    }

    public static void menu() {
        limparConsole();
        System.out.println("=============== MENU ===============");
        System.out.println("1 - ADICIONAR PESSOA");
        System.out.println("2 - REMOVER PESSOA");
        System.out.println("3 - ADICIONAR VEICULO");
        System.out.println("4 - REMOVER VEICULO");
        System.out.println("5 - LISTAR VEICULOS DE 1 PESSOA");
        System.out.println("6 - LISTAR TODAS AS PESSOAS");
        System.out.println("7 - LISTAR PROPRIEDADES DE UMA PESSOA");
        System.out.println("8 - LISTAR TODOS OS VEICULOS");
        System.out.print("\nEscolha uma opção (0 para sair): ");
    }

    public static String solicitarEntrada(String mensagem){
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static void limparConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
        } catch (final Exception e) {
            // Handle any exceptions.
        }
    }

    public static void aguardarInput() {
        System.out.print("\nPressione uma tecla para prosseguir...");
        scanner.nextLine();
    }

    // Adicionar Pessoa (Hashtable e BD)
    public static void opcao1() {

        System.out.println("========== ADICIONAR PESSOA ===========\n");

        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setNif(solicitarEntrada("Informe o NIF: "));
            pessoa.setNome(solicitarEntrada("Informe o nome: "));
            pessoa.setApelido(solicitarEntrada("Informe o apelido: "));
            pessoa.setIdade(solicitarEntrada("Informe a idade: "));

            programController.adicionarPessoa(pessoa);
            System.out.println("\nNova pessoa adicionada com sucesso!");

        } catch (InvalidPersonDataException | SQLException | MoreThanThreeVehiclesException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    // Remover Pessoa (Hashtable e BD)
    public static void opcao2(){

        System.out.println("========== REMOVER PESSOA ===========\n");

        try {
            String nif = solicitarEntrada("Informe o NIF da pessoa a ser removida: ");
            programController.removerPessoa(nif);
            System.out.println("\nRegisto removido com sucesso!");

        } catch (EmptyHashtableException | PersonNotFoundException | SQLException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    // Adicionar Veiculo (Hashtable e BD)
    public static void opcao3() {

        System.out.println("========== ADICIONAR VEICULO ===========\n");

        try {
            Veiculo veiculo;

            String nif = solicitarEntrada("Informe o NIF do proprietário do veículo: ");
            programController.getPessoa(nif);

            String tipoVeiculo = solicitarEntrada("Informe ao tipo de veiculo (1 - Combustão e 2 - Elétrico): ");

            if (tipoVeiculo.equals(Constantes.TIPO_VEICULO_COMBUSTAO))
                veiculo = new VeiculoCombustao();
            else
                veiculo = new VeiculoEletrico();

            veiculo.setMatricula(solicitarEntrada("Informe a matricula do veiculo ('XXXXXX' 6 digitos): "));
            veiculo.setMarca(solicitarEntrada("Informe a marca do veiculo: "));
            veiculo.setModelo(solicitarEntrada("Informe o modelo do veiculo: "));
            veiculo.setChassi(solicitarEntrada("Informe o chassi do veiculo ('XXXXXXXXXXXX' 12 digitos): "));
            veiculo.setLugares(solicitarEntrada("Informe a quantidade de lugares do veiculo: "));
            veiculo.setPortas(solicitarEntrada("Informe a quantidade de portas do veiculo: "));

            if (tipoVeiculo.equals(Constantes.TIPO_VEICULO_COMBUSTAO))
                ((VeiculoCombustao) veiculo).setCilindrada(Integer.parseInt(solicitarEntrada("Informe a cilindrada do veiculo: ")));

            programController.adicionarVeiculo(nif, veiculo);

            System.out.println("\nVeiculo adicionado com sucesso!");

        } catch (EmptyHashtableException | PersonNotFoundException | MoreThanThreeVehiclesException | SQLException |
                 InvalidVehicleDataException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    // Remover Veiculo (Hashtable e BD)
    public static void opcao4(){

        System.out.println("========== REMOVER VEICULO ===========\n");

        try {
            String nif = solicitarEntrada("Informe o NIF do proprietário do veículo: ");
            programController.getPessoa(nif);

            String matricula = solicitarEntrada("Informe a matricula do veículo ('XXXXXX' 6 digitos): ");
            programController.getVeiculo(matricula);

            programController.removerVeiculo(matricula);

            System.out.println("\nVeiculo removido com sucesso!");

        } catch (EmptyHashtableException | VehicleNotFoundException | PersonNotFoundException | SQLException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    // Listar Veiculos Hashtable
    public static void opcao5(){

        System.out.println("========== LISTAR VEICULOS ===========\n");

        try {
            String nif = solicitarEntrada("Informe o NIF do proprietário do(s) veículo(s): ");
            Pessoa pessoa = programController.getPessoa(nif);

            System.out.println(
                    "\nNome completo: " + pessoa.getNomeCompleto() + "\n\n" + pessoa.getVeiculos());

        } catch (EmptyHashtableException | PersonNotFoundException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    // Listar Pessoas Hashtable
    public static void opcao6(){

        System.out.println("========== LISTAR PESSOAS ===========\n");

        try {
            for (Pessoa pessoaCiclo : programController.getPessoas()) {
                System.out.println(pessoaCiclo);
            }

        } catch (EmptyHashtableException e) {
            System.out.println(e.getMessage());
        }
    }

    // Listar Pessoa Hashtable
    public static void opcao7(){

        System.out.println("========== LISTAR PESSOA ===========\n");

        try {
            String nif = solicitarEntrada("Informe o NIF da pessoa: ");
            System.out.println("\n" + programController.getPessoa(nif));

        } catch (EmptyHashtableException | PersonNotFoundException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    // Listar Veiculos Hashtable
    public static void opcao8(){

        System.out.println("========== LISTAR VEICULOS ===========\n");

        try {
            for (Veiculo veiculoCiclo : programController.getVeiculos()) {
                System.out.println(veiculoCiclo);
            }

        } catch (EmptyHashtableException e) {
            System.out.println(e.getMessage());
        }
    }
}