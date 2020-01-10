package com.honeykee.test.example.aop.annotation;

import java.lang.annotation.*;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-18 10:23
 * @since JDK 1.8
 */

@Target( {ElementType.TYPE, ElementType.METHOD} )
@Retention( RetentionPolicy.RUNTIME )
@Documented
@Inherited
public @interface PersonAnnotation {
    String name() default "";
}
