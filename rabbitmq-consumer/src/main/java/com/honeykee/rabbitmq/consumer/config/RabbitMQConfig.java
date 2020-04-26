package com.honeykee.rabbitmq.consumer.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/4/7 4:53 下午
 * @since JDK 1.8
 */


@Configuration
@EnableRabbit
public class RabbitMQConfig implements RabbitListenerConfigurer {


    @Override
    public void configureRabbitListeners( RabbitListenerEndpointRegistrar registrar ) {
        RabbitListenerEndpoint rabbitListenerEndpoint = new SimpleRabbitListenerEndpoint();

    }
}
