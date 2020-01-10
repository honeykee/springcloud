package com.honeykee.test.example.aop.model;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-18 13:45
 * @since JDK 1.8
 */

@Slf4j
public class JDKDynamicObject implements InvocationHandler {

    private Object target;

    public JDKDynamicObject(){
    }

    public Object bind(Object target){
        this.target = target ;
        Object object = Proxy.newProxyInstance( target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this );
        return object;
    }


    @Override
    public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable {
        log.info("1.JDKDynamicObject ==> invoke method : {}，{}，{}", proxy.getClass(), method.getName(),
                args.toString());
        Object invoke = method.invoke( target, args );
        return invoke;
    }
}
