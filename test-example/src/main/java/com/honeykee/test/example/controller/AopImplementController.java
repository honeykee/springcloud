package com.honeykee.test.example.controller;

import com.honeykee.test.example.aop.model.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-18 11:19
 * @since JDK 1.8
 */
@Slf4j
@Controller
@RequestMapping(value="/aop")
public class AopImplementController {
    private Logger logger = LoggerFactory.getLogger(AopImplementController.class);

    @Autowired
    private Person chinese;
    @Autowired
    private American american;

    @ResponseBody
    @RequestMapping(value="/talk")
    public String talk() {
        chinese.say("中国人说汉语");
        american.say("American say english");

        logger.info("11.AopImplementController ==> talk method : [{}]", chinese.getClass());
        logger.info("22.AopImplementController ==> talk method : [{}]", american.getClass());


        logger.info( "-------------------------------------------------------------------------------" );

        JDKDynamicObject dsc1 = new JDKDynamicObject();
        Person person1 = (Person)dsc1.bind(new Chinese());
        String chinaSay = person1.say( "中国人说汉语" );
        logger.info("33.AopImplementController ==> talk method : JDKDynamicObject [{}]", person1.getClass());

        //自定义CGLIB动态代理
        CGLIBDynamicObject dsm = new CGLIBDynamicObject();
        American american1 = (American) dsm.bind(new American());
        String americanSay = american1.say( "American say english" );
        logger.info("44.AopImplementController ==> talk method : CGLIBDynamicObject [{}]", american1.getClass());


        logger.info( "-------------------------------------------------------------------------------" );


        return "success";
    }


    public static void main( String[] args ) {
        log.info( "-------------------------------------------------------------------------------" );

        JDKDynamicObject dsc1 = new JDKDynamicObject();
        Person person1 = (Person)dsc1.bind(new Chinese());
        String chinaSay = person1.say( "中国人说汉语" );
        log.info("33.AopImplementController ==> talk method : JDKDynamicObject [{}]", person1.getClass());

        //自定义CGLIB动态代理
        CGLIBDynamicObject dsm = new CGLIBDynamicObject();
        American american1 = (American) dsm.bind(new American());
        String americanSay = american1.say( "American say english" );
        log.info("44.AopImplementController ==> talk method : CGLIBDynamicObject [{}]", american1.getClass());


        log.info( "-------------------------------------------------------------------------------" );

    }
}