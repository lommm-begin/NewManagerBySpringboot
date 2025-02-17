package com.example.web.factory;

import com.example.web.service.Impl.NewsServiceByJdbcTemplateImpi;
import com.example.web.service.Impl.NewsServiceByMybatisImpl;
import com.example.web.service.NewsService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
@ConfigurationProperties(prefix = "dao")
public class DataSourceFactory implements ApplicationContextAware {
    private ApplicationContext context;
    private Map<String, Supplier<NewsService>> newsServiceAll = new HashMap<>();

    private String mybatis;
    private String jdbcTemplate;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @PostConstruct
    public void setDataSourceFactory() {
        newsServiceAll.put(this.mybatis, ()->this.context.getBean(NewsServiceByMybatisImpl.class));
        newsServiceAll.put(this.jdbcTemplate, ()->this.context.getBean(NewsServiceByJdbcTemplateImpi.class));
    }

    public NewsService getNewsService(String dataSource){
        Supplier<NewsService> newsServiceSupplier = newsServiceAll.get(dataSource);

        if (newsServiceSupplier == null){
            throw new IllegalStateException("参数不合法");
        }

        return newsServiceSupplier.get();
    }

    public void setMybatis(String mybatis) {
        this.mybatis = mybatis;
    }

    public void setJdbcTemplate(String jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
