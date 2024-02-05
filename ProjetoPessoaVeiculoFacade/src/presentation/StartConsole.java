package presentation;

import business.*;
import exceptions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class StartConsole {

    static Scanner scanner = new Scanner(System.in);
    static Pessoa pessoa;
    static String op = "";
    static String nif;
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
            pessoa = new Pessoa();
            System.out.print("Informe o NIF: ");

            pessoa.setNif(scanner.nextLine());
            System.out.print("Informe o nome: ");
            pessoa.setNome(scanner.nextLine());
            System.out.print("Informe o apelido: ");
            pessoa.setApelido(scanner.nextLine());
            System.out.print("Informe a idade: ");
            pessoa.setIdade(scanner.nextLine());

            programController.adicionarPessoa(pessoa);
            System.out.println("\nNova pessoa adicionada com sucesso!");

        } catch (InvalidPersonDataException | SQLException | MoreThanThreeVehiclesException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    // Remover Pessoa (Hashtable e BD)
    public static void opcao2(){
        System.out.println("========== REMOVER PESSOA ===========\n");
        System.out.print("Informe o NIF da pessoa a ser removida: ");
        nif = scanner.nextLine();

        try {
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
            System.out.print("Informe o NIF do proprietário do veículo: ");
            nif = scanner.nextLine();
            programController.getPessoa(nif);

            System.out.print("Informe ao tipo de veiculo (1 - Combustão e 2 - Elétrico): ");
            String tipoVeiculo = scanner.nextLine();
            System.out.print("Informe a matricula do veiculo ('XXXXXX' 6 digitos): ");
            String matricula = scanner.nextLine();
            System.out.print("Informe a marca do veiculo: ");
            String marca = scanner.nextLine();
            System.out.print("Informe o modelo do veiculo: ");
            String modelo = scanner.nextLine();
            System.out.print("Informe o chassi do veiculo ('XXXXXXXXXXXX' 12 digitos): ");
            String chassi = scanner.nextLine();
            System.out.print("Informe a quantidade de lugares do veiculo: ");
            String lugares = scanner.nextLine();
            System.out.print("Informe a quantidade de portas do veiculo: ");
            String portas = scanner.nextLine();

            if (tipoVeiculo.equals("1")){
                System.out.print("Informe a cilindrada do veiculo: ");
                int cilindrada = scanner.nextInt();
                scanner.nextLine();
                programController.adicionarVeiculoCombustao(nif, matricula, marca, modelo, chassi, cilindrada, lugares, portas);
            }
            else {
                programController.adicionarVeiculoEletrico(nif, matricula, marca, modelo, chassi, lugares, portas);
            }

            System.out.println("\nVeiculo adicionado com sucesso!");

        } catch (EmptyHashtableException | PersonNotFoundException | MoreThanThreeVehiclesException | SQLException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    // Remover Veiculo (Hashtable e BD)
    public static void opcao4(){
        System.out.println("========== REMOVER VEICULO ===========\n");

        try {
            System.out.print("Informe o NIF do proprietário do veículo: ");
            nif = scanner.nextLine();
            programController.getPessoa(nif);

            System.out.print("Informe a matricula do veículo ('XXXXXX' 6 digitos): ");
            String matricula = scanner.nextLine();
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
            System.out.print("Informe o NIF do proprietário do(s) veículo(s): ");
            nif = scanner.nextLine();
            pessoa = programController.getPessoa(nif);

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
        System.out.print("Informe o NIF da pessoa: ");
        nif = scanner.nextLine();

        try {
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