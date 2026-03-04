package br.mackenzie.ps2.gerenciador_nomes;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Ihm {
    private final GerenciadorNomes gerenciadorNomes;

    public Ihm(GerenciadorNomes gerenciadorNomes) {
        this.gerenciadorNomes = gerenciadorNomes;
    }

    public void dialogar() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean executando = true;

            while (executando) {
                System.out.println("\n=== Menu Gerenciador de Nomes ===");
                System.out.println("1 - Adicionar nome");
                System.out.println("2 - Listar nomes");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1":
                        System.out.print("Informe o nome: ");
                        String nome = scanner.nextLine().trim();

                        if (nome.isEmpty()) {
                            System.out.println("Nome inválido. Informe um nome não vazio.");
                            break;
                        }

                        try {
                            gerenciadorNomes.adicionar(nome);
                            System.out.println("Nome adicionado com sucesso.");
                        } catch (SQLException e) {
                            System.out.println("Não foi possível adicionar o nome: " + e.getMessage());
                        }
                        break;

                    case "2":
                        try {
                            List<String> nomes = gerenciadorNomes.obter();

                            if (nomes.isEmpty()) {
                                System.out.println("Nenhum nome cadastrado.");
                                break;
                            }

                            System.out.println("Nomes cadastrados:");
                            for (String nomeRegistrado : nomes) {
                                System.out.println("- " + nomeRegistrado);
                            }
                        } catch (SQLException e) {
                            System.out.println("Não foi possível obter os nomes: " + e.getMessage());
                        }
                        break;

                    case "0":
                        executando = false;
                        System.out.println("Encerrando aplicação.");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }
    }
}
