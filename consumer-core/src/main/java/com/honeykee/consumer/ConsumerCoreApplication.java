package com.honeykee.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-12 21:24
 * @since JDK 1.8
 *
 */


@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.honeykee"})
//@EnableHystrix //同 @EnableCircuitBreaker
public class ConsumerCoreApplication {
    public static void main( String[] args ) {
        SpringApplication.run( ConsumerCoreApplication.class ,args);
    }
}
