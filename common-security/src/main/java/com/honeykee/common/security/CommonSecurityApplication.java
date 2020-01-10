package com.honeykee.common.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@SpringBootApplication
//@EnableFeignClients(basePackages = {"com.honeykee.common.feign.clients"})
public class CommonSecurityApplication {

    public static void main( String[] args ) {
        SpringApplication.run( CommonSecurityApplication.class, args );
    }

}
