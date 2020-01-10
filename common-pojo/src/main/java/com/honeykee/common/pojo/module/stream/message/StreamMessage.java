package com.honeykee.common.pojo.module.stream.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-23 20:29
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamMessage {
    private Integer id;
    private String name;
    private String address;
}
