package com.honeykee.stream.consumer.service;

import com.honeykee.common.pojo.module.stream.input.AckInput;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 18:00
 * @since JDK 1.8
 * //消息接受端，stream给我们提供了Sink,Sink源码里面是绑定input的，要跟我们配置文件的imput关联的。
 */
@EnableBinding(AckInput.class)
public class AckTransReceiveService {

    @StreamListener(AckInput.INPUT)
    public void receive( Object payload ){
        System.out.println( "Ack-Input... :" +payload );
    }

}