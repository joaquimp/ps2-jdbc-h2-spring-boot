package br.mackenzie.ps2.gerenciador_nomes;

import java.sql.SQLException;
import java.util.List;

public interface GerenciadorNomes {
    List<String> obter();
    void adicionar(String nome);
}
