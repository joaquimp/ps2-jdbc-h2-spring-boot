package br.mackenzie.ps2.gerenciador_nomes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GerenciadoNomesBD implements GerenciadorNomes {
    private final Connection connection;

    public GerenciadoNomesBD(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<String> obter() throws SQLException {
        String sql = "SELECT nome FROM nomes ORDER BY nome";
        List<String> nomes = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                nomes.add(resultSet.getString("nome"));
            }
        }

        return nomes;
    }

    @Override
    public void adicionar(String nome) throws SQLException {
        String sql = "INSERT INTO nomes (nome) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.executeUpdate();
        }
    }
}
