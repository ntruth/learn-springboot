package com.ucmed.mutidatasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Slf4j
// 移除自动配置的DataSource相关类
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
public class MutiDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutiDatasourceApplication.class, args);
    }

    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDatasourceProperty() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource fooDatasource() {
        DataSourceProperties dataSourceProperties = fooDatasourceProperty();

        log.info(dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager fooTxManager(DataSource fooDatasource) {
        return new DataSourceTransactionManager(fooDatasource);
    }



    @Bean
    @ConfigurationProperties("bar.datasource")
    public DataSourceProperties barDatasourceProperty() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource barDatasource() {
        DataSourceProperties dataSourceProperties = barDatasourceProperty();
        log.info(dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager barTxManager(DataSource barDatasource) {
        return new DataSourceTransactionManager(barDatasource);
    }

}
