package com.honeykee.common.pojo.module.stream.output;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 19:34
 * @since JDK 1.8
 */
public interface AckOutput {

    String OUTPUT="ack-output";

    @Output("ack-output")
    MessageChannel output();

}
