package com.ucmed.hikari;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootApplication
public class HikariApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(HikariApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		insert();
		select();
	}

	public void insert() {
		log.info("开始新增操作+++++++");
		jdbcTemplate.execute("insert into foo(name) value ('张三')");
		log.info("结束新增操作++++++");
	}

	public void select() {
		log.info("开始查询操作--------");
		log.info("sssss:" + jdbcTemplate.queryForObject("select count(*) from foo", Long.class));
		log.info("aaaaa:" + jdbcTemplate.queryForList("select name from foo", String.class));
		log.info("结束查询操作--------");
	}
}
