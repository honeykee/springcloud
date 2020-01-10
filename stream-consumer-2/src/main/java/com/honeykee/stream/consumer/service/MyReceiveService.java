package com.honeykee.stream.consumer.service;

import com.honeykee.common.pojo.module.stream.input.MyInput;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 18:55
 * @since JDK 1.8
 * 自定义的
 */
@EnableBinding(MyInput.class)
public class MyReceiveService {

    @StreamListener(MyInput.INPUT)
    public void receive(Object payload){
        System.out.println("myInput:" + payload);
    }


}
