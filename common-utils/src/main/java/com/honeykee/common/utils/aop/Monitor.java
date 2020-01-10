package com.honeykee.common.utils.aop;

import com.honeykee.common.utils.aop.annotation.AroundLog;
import com.honeykee.common.utils.aop.annotation.BeforeLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-17 14:54
 * @since JDK 1.8
 */
@Aspect
@Slf4j
@Component
public class Monitor {

    /**
     * 定义一个切入点.
     * 解释下：
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 定义在web包或者子包
     * ~ 第三个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(* com.honeykee..service.*.*(..))")
    public void customerService(){}

    @Around( value = "customerService()")
    public Object customerServiceAround( ProceedingJoinPoint joinPoint) throws Throwable {
        return doAround( joinPoint, null );
    }

    @Pointcut(value = "@annotation(com.honeykee.common.utils.aop.annotation.BeforeLog)")
    public void beforePointcut(){}

    @Pointcut(value = "@annotation(com.honeykee.common.utils.aop.annotation.AfterLog)")
    public void afterointcut(){}

    @Pointcut(value = "@annotation(com.honeykee.common.utils.aop.annotation.AfterReturningLog)")
    public void afterReturningPointcut(){}

    @Pointcut(value = "@annotation(com.honeykee.common.utils.aop.annotation.AfterThrowingLog)")
    public void afterThrowingPointcut(){}

    @Pointcut("@annotation(com.honeykee.common.utils.aop.annotation.AroundLog)")
    public void aroundPointcut(){}

    @Before( value = "beforePointcut() && @annotation(beforeLog)")
    public void doBefore( JoinPoint joinPoint, BeforeLog beforeLog ){

    }

    @Around( value = "aroundPointcut() && @annotation(aroundLog)") // @annotation(aroundLog) 才能使用参数AroundLog aroundLog
    public Object doAround( ProceedingJoinPoint joinPoint, AroundLog aroundLog ) {
        if ( null != aroundLog ){
            boolean b = aroundLog.printArgs();
        }

        Object[] args = joinPoint.getArgs();
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            log.info("[zcs1]正常调用方法:[{}], 参数:[{}],使用时间:[{}]ms" , joinPoint ,args, (end - start) );
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            log.error("[zcs1]调用方法异常:[{}], 参数:[{}],使用时间:[{}]ms" , joinPoint ,args, (end - start), e );
//            throw e;
            return null;
        }
    }
}