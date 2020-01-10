package com.honeykee.stream.producer.service;

import com.honeykee.common.pojo.module.stream.ack.SendToBinder;
import com.honeykee.common.pojo.module.stream.output.AckOutput;
import com.honeykee.common.pojo.module.stream.output.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 19:33
 * @since JDK 1.8
 */
@EnableBinding(AckOutput.class)
public class AckSendService {


    @Autowired
    private AckOutput source;


    public void sendMsg(String msg){
        source.output().send( MessageBuilder.withPayload(msg).build());
    }
}
