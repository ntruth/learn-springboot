package com.ucmed.singledatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class SingleDatasourceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SingleDatasourceApplication.class, args);
	}


	@Autowired
	DataSource dataSource;

	@Override
	public void run(String... args) {
		showConnection();
	}

	public void showConnection() {
		try {
			System.out.println(dataSource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
