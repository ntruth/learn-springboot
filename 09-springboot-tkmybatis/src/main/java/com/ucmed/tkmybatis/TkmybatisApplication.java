package com.ucmed.tkmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.ucmed.tkmybatis.mapper")
@SpringBootApplication
public class TkmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkmybatisApplication.class, args);
    }

}
