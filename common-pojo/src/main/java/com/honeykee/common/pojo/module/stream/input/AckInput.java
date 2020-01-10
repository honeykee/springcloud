package com.honeykee.common.pojo.module.stream.input;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 18:52
 * @since JDK 1.8
 */
public interface AckInput {

    /**
     * 这个值和 spring.cloud.stream.bindings.{input}.destination 对应
     */
    String INPUT = "ack-input";

    /**
     * 自定义 input
     * @return
     * 这个Input 代表注册到容器的组件名称 bean name 不设置容器默认为input 如果其他地方配置了Input 那么会报错
     * 另外需要和上面的INPUT一致
     */
    @Input("ack-input")
    SubscribableChannel input();

}
