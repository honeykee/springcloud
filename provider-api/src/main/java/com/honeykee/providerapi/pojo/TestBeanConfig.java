package com.honeykee.providerapi.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-11-06 10:47
 * @since JDK 1.8
 */

@Configuration
@Order(value = 0 )
public class TestBeanConfig {

    @Bean
    @ConfigurationProperties(prefix = "section2")
    public TestBean getTestBean(){
        TestBean testBean = new TestBean();
        return testBean;
    }
}
