package com.honeykee.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author gemii
 * 通过@EnableTurbine接口，激活对Turbine的支持。
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {

    public static void main( String[] args ) {
        SpringApplication.run( HystrixDashboardApplication.class, args );
    }

}
