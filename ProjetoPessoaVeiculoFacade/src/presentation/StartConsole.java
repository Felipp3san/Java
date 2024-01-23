package presentation;

import business.Pessoa;
import business.Veiculo;
import business.ProgramController;
import exceptions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StartConsole {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ProgramController programController = null;

        Pessoa pessoa;
        Veiculo veiculo;
        String op = "";
        String nif;
        String matricula;

        /*
         * Tenta estabelecer conexão com a base de bases,
         * caso não tenha sucesso, encerra o programa.
         */
        try {
            programController = new ProgramController();
        } catch (SQLException e) {
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
                    /*
                     * cria uma nova instância de pessoa e envia
                     * para a hashtable da classe 'gestor de pessoas'.
                     */
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

                    } catch (InvalidPersonDataException | SQLException e) {
                        System.out.println("\n" + e.getMessage());
                    }

                    break;
                case "2":
                    System.out.println("========== REMOVER PESSOA ===========\n");
                    System.out.print("Informe o NIF da pessoa a ser removida: ");
                    nif = scanner.nextLine();

                    try {
                        pessoa = programController.getPessoa(nif);
                        for (Veiculo veiculoCiclo : pessoa.getVeiculos().reversed()) {
                            matricula = veiculoCiclo.getMatricula();
                            programController.removerVeiculo(matricula, pessoa);
                        }
                        programController.removerPessoa(nif);
                        System.out.println("\nRegisto removido com sucesso!");
                    } catch (EmptyHashtableException | PersonNotFoundException | VehicleNotFoundException e) {
                        System.out.println("\n" + e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("========== ADICIONAR VEICULO ===========\n");

                    try {
                        System.out.print("Informe o NIF do proprietário do veículo: ");
                        nif = scanner.nextLine();
                        pessoa = programController.getPessoa(nif);

                        veiculo = new Veiculo();
                        System.out.print("Informe a matricula do veiculo ('XXXXXX' 6 digitos): ");
                        veiculo.setMatricula(scanner.nextLine());
                        System.out.print("Informe a marca do veiculo: ");
                        veiculo.setMarca(scanner.nextLine());
                        System.out.print("Informe o modelo do veiculo: ");
                        veiculo.setModelo(scanner.nextLine());
                        System.out.print("Informe o chassi do veiculo ('XXXXXXXXXXXX' 12 digitos): ");
                        veiculo.setChassi(scanner.nextLine());
                        System.out.print("Informe a cilindrada do veiculo: ");
                        veiculo.setCilindrada(scanner.nextLine());
                        System.out.print("Informe a quantidade de lugares do veiculo: ");
                        veiculo.setLugares(scanner.nextLine());
                        System.out.print("Informe a quantidade de portas do veiculo: ");
                        veiculo.setPortas(scanner.nextLine());

                        programController.adicionarVeiculo(veiculo.getMatricula(), veiculo, pessoa);

                        System.out.println("\nVeiculo adicionado com sucesso!");

                    } catch (EmptyHashtableException | PersonNotFoundException | MoreThanThreeVehiclesException
                            | InvalidVehicleDataException e) {
                        System.out.println("\n" + e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("========== REMOVER VEICULO ===========\n");

                    try {
                        System.out.print("Informe o NIF do proprietário do veículo: ");
                        nif = scanner.nextLine();
                        pessoa = programController.getPessoa(nif);

                        System.out.print("Informe a matricula do veículo ('XXXXXX' 6 digitos): ");
                        matricula = scanner.nextLine();
                        programController.getVeiculo(matricula);

                        programController.removerVeiculo(matricula, pessoa);

                        System.out.println("\nVeiculo removido com sucesso!");

                    } catch (EmptyHashtableException | VehicleNotFoundException | PersonNotFoundException e) {
                        System.out.println("\n" + e.getMessage());
                    }
                    break;
                case "5":
                    System.out.println("========== LISTAR VEICULOS ===========\n");

                    try {
                        System.out.print("Informe o NIF do proprietário do(s) veículo(s): ");
                        nif = scanner.nextLine();

                        pessoa = programController.getPessoa(nif);

                        System.out.println(
                                "\nNome completo: " + pessoa.getNomeCompleto() + "\n\n" + pessoa.getVeiculos());

                    } catch (EmptyHashtableException | VehicleNotFoundException | PersonNotFoundException e) {
                        System.out.println("\n" + e.getMessage());
                    }
                    break;
                case "6":
                    /*
                     * Recebe uma coleção com todos os registos de pessoas
                     * e imprime-os todos.
                     */
                    System.out.println("========== LISTAR PESSOAS ===========\n");
                    try {
                        for (Pessoa pessoaCiclo : programController.getPessoas()) {
                            System.out.println(pessoaCiclo);
                        }
                    } catch (EmptyHashtableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "7":
                    /*
                     * Imprime as propriedades da pessoa com o nif inserido.
                     */
                    System.out.println("========== LISTAR PESSOA ===========\n");
                    System.out.print("Informe o NIF da pessoa: ");
                    nif = scanner.nextLine();
                    try {
                        System.out.println("\n" + programController.getPessoa(nif));
                    } catch (EmptyHashtableException | PersonNotFoundException e) {
                        System.out.println("\n" + e.getMessage());
                    }
                    break;
                case "8":
                    System.out.println("========== LISTAR VEICULOS ===========\n");
                    try {
                        for (Veiculo veiculoCiclo : programController.getVeiculos()) {
                            System.out.println(veiculoCiclo);
                        }
                    } catch (EmptyHashtableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "9":
                    System.out.println("========== LISTAR PESSOAS DA BD ===========\n");
                    try {
                        ResultSet pessoas = programController.buscarPessoasBD();

                        while (pessoas.next()) {
                            System.out.println("NIF: " + pessoas.getInt(1) +
                                    " - NOME: " + pessoas.getString(2) + " " + pessoas.getString(3));
                        }

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("\nOpção inválida.");
                    break;
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
        System.out.println("9 - LISTAR TODAS AS PESSOAS NA BASE DE DADOS");
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
}