package br.mackenzie.ps2.gerenciador_nomes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

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
			System.out.println("EXECUTING : command line runner");
			String createTable = "CREATE TABLE IF NOT EXISTS countries(id INT, name VARCHAR(256),PRIMARY KEY (id));";
			String databaseURL = "~/data/demo;"; // "testdb" //Esse segundo o banco será executado somente na memória
			Connection conn = DriverManager
					.getConnection(
							"jdbc:h2:file:" + databaseURL + "INIT=" + createTable,
							"admin", "admin");

			Banco b = new Banco(conn);
			b.printData();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
