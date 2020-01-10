package com.honeykee.test.example.aop.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-18 13:45
 * @since JDK 1.8
 */

@Slf4j
public class CGLIBDynamicObject implements InvocationHandler {

    private Object target;

    public CGLIBDynamicObject(){
    }

    public Object bind(Object target){
        this.target = target;
        Enhancer enhancer  =  new Enhancer();
        enhancer.setSuperclass( this.target.getClass() );
        enhancer.setCallback( this );
        return enhancer.create();
    }


    @Override
    public Object invoke( Object object, Method method, Object[] args ) throws Throwable {
        log.info("2.CGLIBDynamicObject ==> invoke method : {}，{}，{}", object.getClass(), method.getName(),
                args.toString());
        Object invoke = method.invoke( target, args );
        return invoke;
    }
}
