package com.honeykee.config.client.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-26 14:19
 * @since JDK 1.8
 */
@Component
@Slf4j
public class TaskApplicationRunner implements ApplicationRunner {

    @Override
    public void run( ApplicationArguments args ) throws Exception {
        log.info( "Hello World from Spring Cloud Task---------" );

    }
}
