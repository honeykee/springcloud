package com.honeykee.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
@ComponentScan(basePackages = {"com.honeykee"})
public class UserServiceApplication {

    public static void main( String[] args ) {
        SpringApplication.run( UserServiceApplication.class, args );
    }

}
