package com.honeykee.stream.producer.service;

import com.honeykee.common.pojo.module.stream.message.StreamMessage;
import com.honeykee.common.pojo.module.stream.output.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 17:49
 * @since JDK 1.8
 *
 */
//这个注解给我们绑定消息通道的，Source是Stream给我们提供的，可以点进去看源码，可以看到output和input,这和配置文件中的output，input对应的。

//@EnableBinding(Source.class)
@EnableBinding(MySource.class)
public class SendService {

//    @Autowired
//    private Source source;

    @Autowired
    private MySource source;


    public void sendMsg( StreamMessage msg){
//        source.output().send( MessageBuilder.withPayload(msg).build());
        source.myOutput().send( MessageBuilder.withPayload(msg).build());
    }

}