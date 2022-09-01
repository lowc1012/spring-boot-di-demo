package com.example.dependencyinjection;

import com.example.dependencyinjection.config.ProducerConfig;
import com.example.dependencyinjection.service.AzureEventHubProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.example.dependencyinjection.service", "com.example.dependencyinjection.config"})
public class DependencyInjectionApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DependencyInjectionApplication.class, args);

        ProducerConfig config = applicationContext.getBean(ProducerConfig.class);
        String appName = config.getCommonProperties().getName();
        System.out.println(appName + " running...");

        AzureEventHubProducer producer = applicationContext.getBean(AzureEventHubProducer.class);
        producer.sendMessage();

    }

}
