package com.honeykee.common.utils.aop.annotation;

import java.lang.annotation.*;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-17 16:32
 * @since JDK 1.8
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Retention( RetentionPolicy.RUNTIME )
public @interface AfterReturningLog {
    String description() default "";
    boolean printArgs() default  true;// 默认打印参数
    boolean printResult() default false;
    boolean printSize() default false;//针对返回的数据是集合类型
}
