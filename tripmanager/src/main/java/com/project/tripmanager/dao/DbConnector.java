package com.project.tripmanager.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DbConnector {
	@Bean
    public DataSource dataSource() {
        // Configure your DataSource implementation here
        // Example: DriverManagerDataSource, BasicDataSource, etc.
        return new DriverManagerDataSource("jdbc:mysql://localhost:3308/rgexplorer", "root", "root");
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
