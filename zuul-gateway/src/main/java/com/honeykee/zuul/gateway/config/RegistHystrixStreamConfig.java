package com.honeykee.zuul.gateway.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-14 11:52
 * @since JDK 1.8
 */

@Configuration
public class RegistHystrixStreamConfig {

    @Bean
    public ServletRegistrationBean registrationBean(){
        System.out.println("---");
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup( 1 );
        registrationBean.setName( "HystrixMetricsStreamServlet" );
        registrationBean.addUrlMappings( "/actuator/hystrix.stream" );
        return registrationBean;
    }

}
