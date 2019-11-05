package com.datasource;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresDataSource {

    //Instantiating this HikariDataSource as a bean
    //We will create a configuration file "app.datasource"
    //It will look at our application.yml to extract the data
    //This will contain username, url, password, etc.
    @Bean
    @ConfigurationProperties("app.datasource")
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder
        .create()
        .type(HikariDataSource.class)
        .build();
    }

}