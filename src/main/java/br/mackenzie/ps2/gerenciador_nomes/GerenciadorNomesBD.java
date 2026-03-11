package br.mackenzie.ps2.gerenciador_nomes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorNomesBD implements GerenciadorNomes {
    private final Connection connection;

    public GerenciadorNomesBD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<String> obter() {
        String sql = "SELECT * FROM nomes ORDER BY nome";
        List<String> nomes = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                nomes.add(resultSet.getString("nome"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter nomes " + e);
            return new ArrayList<>();
        }

        return nomes;
    }

    @Override
    public boolean adicionar(String nome) {
        String sql = "INSERT INTO nomes (nome) VALUES (?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remover(String nome) {
        String sql = "DELETE FROM nomes WHERE nome = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            int qtd = stmt.executeUpdate();
            return qtd > 0;
        } catch(Exception ex) {
            return false;
        }
    }

    @Override
    public boolean atualizar(String nomeAntigo, String novoNome) {
        String sql = "UPDATE nomes SET nome=? WHERE nome=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(2, nomeAntigo);
            stmt.setString(1, novoNome);
            int qtd = stmt.executeUpdate();
            return qtd > 0;
        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
