package com.gilab.wjj.persistence.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Created by yuankui on 10/31/17.
 */
@Configuration
@EnableAutoConfiguration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@ComponentScan("com.gilab.wjj.persistence")
@MapperScan("com.gilab.wjj.persistence.mapper")
@PropertySources(value = {
        @PropertySource(value = {"classpath:/datasource.properties"})
})
public class FinancePersistenceBootConfig {
}
