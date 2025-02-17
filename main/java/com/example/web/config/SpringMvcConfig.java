package com.example.web.config;

import com.example.web.interceptor.ControllerStatisticsInterceptor;
import com.example.web.interceptor.NewsInterceptor;
import com.example.web.interceptor.SearchInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.List;
import java.util.Properties;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/login").setViewName("user/login");
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();

        properties.setProperty("java.lang.Exception", "exception");
        exceptionResolver.setExceptionMappings(properties);
        exceptionResolver.setExceptionAttribute("exception");
        resolvers.add(exceptionResolver);
    }

    @Bean
    public NewsInterceptor newsInterceptor() {
        return new NewsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //输出
        registry.addInterceptor(new ControllerStatisticsInterceptor())
                .addPathPatterns("/**");

        //拦截管理搜索页面
        registry.addInterceptor(new SearchInterceptor())
                .addPathPatterns("/admin/Search");

        //拦截未登录用户
        registry.addInterceptor(newsInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/Search");
    }
}
