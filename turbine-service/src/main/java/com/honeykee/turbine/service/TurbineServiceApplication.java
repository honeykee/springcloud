package com.honeykee.turbine.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
//import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * @author gemii
 */
@SpringBootApplication
@EnableTurbine
@EnableEurekaClient
//@EnableTurbineStream
public class TurbineServiceApplication {

    public static void main( String[] args ) {
        SpringApplication.run( TurbineServiceApplication.class, args );
    }

}

