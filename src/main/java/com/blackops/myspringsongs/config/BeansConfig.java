package com.blackops.myspringsongs.config;

import org.apache.commons.dbcp2.BasicDataSource;
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

    @Bean
    public DataSource stagingDataSource(
            @Value("${my.drivername}")String drivername
            ,@Value("${my.url}")String url
            ,@Value("${my.username}")String username
            ,@Value("${my.password}")String password
    ) {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(drivername);
        bds.setUrl(url);
        bds.setUsername(username);
        bds.setPassword(password);
        return bds;
    }
}
