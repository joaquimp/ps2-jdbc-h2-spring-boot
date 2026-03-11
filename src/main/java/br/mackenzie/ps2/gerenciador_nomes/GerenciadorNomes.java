package br.mackenzie.ps2.gerenciador_nomes;

import java.util.List;

public interface GerenciadorNomes {
    List<String> obter();
    boolean adicionar(String nome);
    boolean remover(String nome);
    boolean atualizar(String nomeAntigo, String novoNome);
}
