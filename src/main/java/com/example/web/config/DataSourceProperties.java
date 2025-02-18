package com.example.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties("datasource")
public class DataSourceProperties {
    private Map<String, DataSourceValue> map = new HashMap<>();

    public static class DataSourceValue {
        private String driverClassName;

        private String url;

        private String username;

        private String password;

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public Map<String, DataSourceValue> getMap() {
        return map;
    }

    public void setMap(Map<String, DataSourceValue> map) {
        this.map = map;
    }
}
