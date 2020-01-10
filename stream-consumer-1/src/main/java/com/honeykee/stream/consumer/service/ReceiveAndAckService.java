package com.honeykee.stream.consumer.service;

import com.honeykee.common.pojo.module.stream.ack.SendToBinder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 19:33
 * @since JDK 1.8
 */
@EnableBinding(SendToBinder.class)
public class ReceiveAndAckService {

    /**
     * 返回Ack 但是实验不明确，condition 条件
     * @param payload
     * @return
     */
    @StreamListener(value = "ack-input", condition = "payload.id >= 10" )
    @SendTo("ack-output")
    public Object receiveFromInput(Object payload){
        System.out.println("ack-确认消息..."+payload);
        return "ack return ...";
    }
}
