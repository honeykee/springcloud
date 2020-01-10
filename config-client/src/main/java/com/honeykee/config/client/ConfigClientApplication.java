package com.honeykee.config.client;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.task.configuration.EnableTask;

/**
 * @author gemii
 */
@SpringBootApplication
@EnableEurekaClient
@EnableTask
@EnableBatchProcessing
public class ConfigClientApplication {

    public static void main( String[] args ) {
        SpringApplication.run( ConfigClientApplication.class, args );
    }

}
