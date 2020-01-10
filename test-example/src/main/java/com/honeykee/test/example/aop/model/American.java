package com.honeykee.test.example.aop.model;

import com.honeykee.test.example.aop.annotation.PersonAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-18 10:25
 * @since JDK 1.8
 */

@Slf4j
@Component
public class American {

    public American() {
        super();
        log.info("American ==> American method : 正在生成一个American实例");
    }

    @PersonAnnotation(name="American")//该注解是用来定义切点
    public String say(String name) {
        log.info("American ==> say method : say {}", name);
        return name + " hello, CGLIB implement AOP";
    }

    public void eat(String food) {
        log.info("American ==> eat method : eat {}", food);
    }

}
