package com.honeykee.auth.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.Arrays;

/**
 * @author zcs
 * @EnableResourceServer注解，开启 Resource Server。程序需要对外暴露 获取 Token 的 API接口和验证 Token 的 API接口 ，所以该程序也是一个资源服务。
 *
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableResourceServer
@ComponentScan(basePackages = {"com.honeykee"})
@EnableFeignClients(basePackages = {"com.honeykee.common.feign.clients"})
public class AuthServerApplication {

    public static void main( String[] args ) {
        SpringApplication.run( AuthServerApplication.class, args );
    }


}
