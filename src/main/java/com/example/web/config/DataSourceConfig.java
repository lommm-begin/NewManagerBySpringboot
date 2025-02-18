package com.example.web.config;

import com.example.web.datasource.routing.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    /**
     * 默认提供mysql和Oracle的数据源
     *
     * 默认使用mysql
     * @return
     */
    @Bean
    public DataSource dataSourceByMysql() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();

        for (Map.Entry<String, DataSourceProperties.DataSourceValue> entry : dataSourceProperties.getMap().entrySet()) {
            HikariDataSource hikariDataSource = new HikariDataSource();
            hikariDataSource.setDriverClassName(entry.getValue().getDriverClassName());
            hikariDataSource.setJdbcUrl(entry.getValue().getUrl());
            hikariDataSource.setUsername(entry.getValue().getUsername());
            hikariDataSource.setPassword(entry.getValue().getPassword());

            dataSourceMap.put(entry.getKey(), hikariDataSource);
        }

        dynamicDataSource.setTargetDataSources(dataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMap.get("default"));

        return dynamicDataSource;
    }
}
