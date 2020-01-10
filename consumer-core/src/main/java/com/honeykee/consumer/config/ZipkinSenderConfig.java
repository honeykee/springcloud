package com.honeykee.consumer.config;

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

    /**
     * 配置zipkin发送的rabbit的服务地址
     * @return
     */
    @Bean
    public Sender getSender(){
        Sender sender =  RabbitMQSender.newBuilder()
                .addresses( "localhost" ).virtualHost( "zipkin" )
                .username( "guest" ).password( "guest" ).build();

        return sender;
    }
}
