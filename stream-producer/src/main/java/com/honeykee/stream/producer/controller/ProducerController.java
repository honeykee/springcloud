package com.honeykee.stream.producer.controller;

import com.honeykee.common.pojo.module.stream.message.StreamMessage;
import com.honeykee.stream.producer.service.AckSendService;
import com.honeykee.stream.producer.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 17:51
 * @since JDK 1.8
 */
@RestController
public class ProducerController {


    @Autowired
    private SendService sendService;

    @Autowired
    private AckSendService ackSendService;


    @GetMapping("/send/{id}")
    public String send(@PathVariable("id") Integer id){

        StreamMessage message = new StreamMessage( id ,"zcs", "SH");


        sendService.sendMsg( message );

        ackSendService.sendMsg( " ack-message " );
        return "success";
    }
}
