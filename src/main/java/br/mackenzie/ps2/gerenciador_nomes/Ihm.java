package br.mackenzie.ps2.gerenciador_nomes;

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
                System.out.println("3 - Apagar");
                System.out.println("4 - alterar");
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

                        if(gerenciadorNomes.adicionar(nome)) {
                            System.out.println("Nome adicionado com sucesso.");
                        } else {
                            System.out.println("Deu ruim ao adicionar");
                        }
                        
                        break;

                    case "2":
                        List<String> nomes = gerenciadorNomes.obter();

                        if (nomes.isEmpty()) {
                            System.out.println("Nenhum nome cadastrado.");
                            break;
                        }

                        System.out.println("Nomes cadastrados:");
                        for (String nomeRegistrado : nomes) {
                            System.out.println("- " + nomeRegistrado);
                        }
                        
                        break;

                    case "3":
                        System.out.print("Informe o nome a ser apagado: ");
                        String nomeApagar = scanner.nextLine().trim();

                        if (nomeApagar.isEmpty()) {
                            System.out.println("Nome inválido. Informe um nome não vazio.");
                            break;
                        }

                        if(gerenciadorNomes.remover(nomeApagar)) {
                            System.out.println("Nome removido com sucesso.");
                        } else {
                            System.out.println("Deu ruim ao apagar");
                        }
                        
                        break;
                    case "4":
                        System.out.print("Informe o nome original: ");
                        String nomeOriginal = scanner.nextLine().trim();
                        System.out.print("Informe o nome novo: ");
                        String nomeNovo = scanner.nextLine().trim();

                        if (nomeOriginal.isEmpty() || nomeNovo.isEmpty()) {
                            System.out.println("Nome inválido. Informe um nome não vazio.");
                            break;
                        }

                        if(gerenciadorNomes.atualizar(nomeOriginal, nomeNovo)) {
                            System.out.println("Nome alterado com sucesso.");
                        } else {
                            System.out.println("Deu ruim ao alterar");
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
