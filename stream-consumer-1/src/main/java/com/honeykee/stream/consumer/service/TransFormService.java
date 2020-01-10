package com.honeykee.stream.consumer.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 18:25
 * @since JDK 1.8
 * springcloud-stream还给我们提供了一个Processor接口，用于进行消息处理后再进行发送出去，相当于一个消息中转站。下面我们进行演示
 */

@EnableBinding(Processor.class)
public class TransFormService {

    /**
     *
     * @param payload
     * @return
     * Processor 指定接收那个input 和 指定发到那个output
     */
    @ServiceActivator(inputChannel = Processor.INPUT,outputChannel = Processor.OUTPUT)
    public Object transform(Object payload){
        System.out.println("消息中转站收到："+payload);
        return "消息中转站："+payload ;
    }
}
