package com.blackops.myspringsongs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;


@PropertySource("classpath:datasource.properties")
@Configuration
public class BeansConfig {

    @Autowired
    Environment env;

    @Primary
    @Profile("dev")
    @Bean
    public DataSource getDataSourceMem() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
        dataSourceBuilder.username("SHAI");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }


    @Profile("qa")
    @Bean
    public DataSource getDataSourceFile(@Value("${my.drivername}") String driver) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
        return dataSourceBuilder.build();
    }
}
