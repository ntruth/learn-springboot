package com.ucmed.druid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootApplication
public class DruidApplication implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DruidApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(dataSource.toString());
        insert();
        select();
    }

    public void insert() {
        jdbcTemplate.execute("insert into foo(name) values ('lisi')");
    }

    public void select() {
        log.info("开始查询操作--------");
        log.info("总共有:" + jdbcTemplate.queryForObject("select count(*) from foo", Long.class));
        log.info("查询结果为:" + jdbcTemplate.queryForList("select name from foo", String.class));
        log.info("结束查询操作--------");
    }
}
