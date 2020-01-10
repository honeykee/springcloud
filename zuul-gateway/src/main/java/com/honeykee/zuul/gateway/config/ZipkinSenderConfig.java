package com.honeykee.zuul.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.reporter.Sender;
import zipkin2.reporter.amqp.RabbitMQSender;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-20 12:34
 * @since JDK 1.8
 */
@Configuration
public class ZipkinSenderConfig {


    @Bean
    public Sender getSender(){
        //queue是zipkin服务端连接rabbitmq自动创建的
        Sender sender =  RabbitMQSender.newBuilder()
                .addresses( "localhost:5672" ).virtualHost( "zipkin" ).queue( "zipkin" )
                .username( "guest" ).password( "guest" ).build();
        return sender;
    }
}
