package com.honeykee.providerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author gemii
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.honeykee.common.feign.clients"})
@ComponentScan(basePackages = {"com.honeykee"})
public class ProviderApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApiApplication.class, args);
    }

}
