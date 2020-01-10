package com.honeykee.stream.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-24 17:22
 * @since JDK 1.8
 */

@RestController
public class ConsumerController {

    @GetMapping("/get")
    public String getStr(){
        return "success";
    }
}
