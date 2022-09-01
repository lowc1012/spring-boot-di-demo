package com.example.dependencyinjection.config;

import com.example.dependencyinjection.config.property.AzureEventHubProperties;
import com.example.dependencyinjection.config.property.CommonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({CommonProperties.class, AzureEventHubProperties.class})
public class ProducerConfig {
    private CommonProperties commonProperties;

    private AzureEventHubProperties azureEventHubProperties;

    public ProducerConfig(CommonProperties commonProperties, AzureEventHubProperties azureEventHubProperties) {
        this.commonProperties = commonProperties;
        this.azureEventHubProperties = azureEventHubProperties;
    }

    public AzureEventHubProperties getAzureEventHubProperties() {
        return azureEventHubProperties;
    }

    public CommonProperties getCommonProperties() {
        return commonProperties;
    }

    public void setAzureEventHubProperties(AzureEventHubProperties azureEventHubProperties) {
        this.azureEventHubProperties = azureEventHubProperties;
    }

    public void setCommonProperties(CommonProperties commonProperties) {
        this.commonProperties = commonProperties;
    }
}
