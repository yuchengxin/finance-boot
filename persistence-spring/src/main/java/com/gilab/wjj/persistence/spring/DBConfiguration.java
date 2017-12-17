package com.gilab.wjj.persistence.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.sql.DataSource;

/**
 * Created by yuankui on 10/31/17.
 */

@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@PropertySources(value = {
        @PropertySource(value = {"classpath:db.properties"}),
        @PropertySource(value = {"classpath:datasource.properties"})
})
public class DBConfiguration {

    @Value("${db.url}")
    private String url;

    @Value("${db.driver}")
    private String driver;

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        return builder.url(url)
                .driverClassName(driver)
                .username(user)
                .password(password)
                .build();
    }

}
