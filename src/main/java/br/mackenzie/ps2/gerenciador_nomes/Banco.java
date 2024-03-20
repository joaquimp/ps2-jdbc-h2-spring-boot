package br.mackenzie.ps2.gerenciador_nomes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Banco {
  private Connection conn;

  public Banco(Connection conn) throws SQLException {
    this.conn = conn;
    insertInitialData();
  }

  private void insertInitialData() {
    try {
      String sql = """
          INSERT INTO countries (id, name) VALUES (1, 'USA');
          INSERT INTO countries (id, name) VALUES (2, 'France');
          INSERT INTO countries (id, name) VALUES (3, 'Brazil');
          INSERT INTO countries (id, name) VALUES (4, 'Italy');
          INSERT INTO countries (id, name) VALUES (5, 'Canada');
          """;

      PreparedStatement pstmt = conn.prepareStatement(sql);
      int result = pstmt.executeUpdate();

      System.out.println("Banco inicializado, foram adicionado " + result + " novos registros");
    } catch (SQLException e) {
      System.out.println("Dados iniciais não foram adicionados");
    }

  }

  public void printData() throws SQLException {
    PreparedStatement pstmt = conn.prepareStatement("select * from countries");
    ResultSet rs = pstmt.executeQuery();

    while (rs.next()) {
      System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
    }
  }

}
