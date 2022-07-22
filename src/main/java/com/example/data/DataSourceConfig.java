package com.example.data;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(System.getenv("psql_url"));
        dataSourceBuilder.username(System.getenv("psql_usr"));
        dataSourceBuilder.password(System.getenv("psql_password"));
        return dataSourceBuilder.build();
    }
}
