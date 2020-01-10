package com.honeykee.test.example.aop.model;

import com.honeykee.test.example.aop.annotation.PersonAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-18 10:20
 * @since JDK 1.8
 */

@Component
@Slf4j
public class Chinese implements Person {

    public Chinese(){
        super();
        log.info("Chinese ==> Chinese method : 正在生成一个Chinese实例");
    }

    @PersonAnnotation(name="Chinese")//该注解是用来定义切点
    @Override
    public String say( String name ) {
        log.info("Chinese ==> say method : say {}", name);
        return name + " hello, JDK implement AOP";
    }

    @Override
    public void eat( String food ) {
        log.info("Chinese ==> eat method : eat {}", food);

    }
}
