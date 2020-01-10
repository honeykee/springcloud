package com.honeykee.test.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true) //proxyTargetClass 不能控制JDK代理，依然是CGLib
public class TestExampleApplication {

    public static void main( String[] args ) {
        SpringApplication.run( TestExampleApplication.class, args );
    }

}
