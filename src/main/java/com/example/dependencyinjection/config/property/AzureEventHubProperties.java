package com.example.dependencyinjection.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

// Constructor binding
// To use constructor binding the class must be enabled using
// @EnableConfigurationProperties or configuration property scanning.
// You cannot use constructor binding with beans that are created by the regular Spring mechanisms
// (for example @Component beans, beans created by using @Bean methods or beans loaded by using @Import)
@ConstructorBinding // This annotation is used to indicate that constructor binding should be used
@ConfigurationProperties(prefix = "azure.eventhub")
public class AzureEventHubProperties {
    private String connectionString;
    private String name;

    public AzureEventHubProperties(String connectionString, String name) {
        this.connectionString = connectionString;
        this.name = name;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
