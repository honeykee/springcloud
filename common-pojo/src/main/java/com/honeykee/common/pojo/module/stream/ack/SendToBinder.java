package com.honeykee.common.pojo.module.stream.ack;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 19:23
 * @since JDK 1.8
 * 消息接收ack 机制，我们接收到消息后，给别人一个反馈ACK，SpringCloud stream 给我们提供了一个SendTo注解可以帮我们干这些事情。
 */
public interface SendToBinder {

    @Output("ack-output")
    MessageChannel output();

    @Input("ack-input")
    SubscribableChannel input();
}
