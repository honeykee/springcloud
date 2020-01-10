package com.honeykee.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-25 17:40
 * @since JDK 1.8
 */

@RestController
@RefreshScope
public class ConfigController {

    @Value( "${test.name}" )
    private String name;

    @GetMapping("/get/test/name")
    public String getServerPort(){
        return "test.name: " + name;
    }
}
