package br.mackenzie.ps2.gerenciador_nomes;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorNomesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorNomesApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			String createTable = "CREATE TABLE IF NOT EXISTS nomes(nome VARCHAR(256) NOT NULL UNIQUE);";
			String file = "file:./data/banco_dados;";
			String url = "jdbc:h2:" + file + "INIT=" + createTable;
			try {
				Connection conn = DriverManager.getConnection(url,"admin", "admin");
				GerenciadorNomes gerenciadorNomes = new GerenciadorNomesBD(conn);
				Ihm ihm = new Ihm(gerenciadorNomes);
				ihm.dialogar();
			} catch(Exception e) {
				e.printStackTrace();
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
