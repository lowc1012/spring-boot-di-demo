package com.example.dependencyinjection.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

// JavaBean properties binding
@ConfigurationProperties(prefix = "app.common")
public class CommonProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private final DatabaseProperties db = new DatabaseProperties();
    public static class DatabaseProperties {
        private String name;
        private String user;
        private String password;

        public String getName() {
            return name;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public DatabaseProperties getDb() {
        return db;
    }
}
