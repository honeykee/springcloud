package com.honeykee.common.pojo.module.stream.output;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 18:13
 * @since JDK 1.8
 */
public interface MySource {
    /**
     * myOutput是要和你的配置文件的消息发送端配置对应的，因此修改springcloud-stream中application.yml配置
     * @return
     */
    @Output("myOutput")
    MessageChannel myOutput();
}
