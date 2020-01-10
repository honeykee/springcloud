package com.honeykee.zuul.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author gemii
 */
@SpringBootApplication
//@EnableZuulServer //高级
@EnableZuulProxy
@EnableEurekaClient

public class ZuulGatewayApplication {

    public static void main( String[] args ) {
        SpringApplication.run( ZuulGatewayApplication.class, args );
    }

}
