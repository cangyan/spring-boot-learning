package com.huuinn.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DBConfig {
    @Bean(name = "learningDataSource")
    @Qualifier("learningDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.learning")
    public DataSource learningDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "learningJdbcTemplate")
    public JdbcTemplate learningJdbcTemplate(@Qualifier("learningDataSource") DataSource dataSource) {
        return new JdbcTemplate((dataSource));
    }
}
