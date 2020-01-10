package com.honeykee.test.example.aop.aspect;

import com.honeykee.test.example.aop.annotation.PersonAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-18 10:26
 * @since JDK 1.8
 */
@Slf4j
@Aspect
@Component
public class PersonAspect {

    @Pointcut(value = "@annotation(com.honeykee.test.example.aop.annotation.PersonAnnotation)")
    public void pointCut(){}

    @Before( value = "pointCut() && @annotation(personAnnotation)")
    public void doBefore( JoinPoint joinPoint, PersonAnnotation personAnnotation ){
        log.info("PersonAspect ==> before method : {}", joinPoint.getClass());
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint){
        log.info("PersonAspect ==> after method : {}", joinPoint.getClass());
    }

}
