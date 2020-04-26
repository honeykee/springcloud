package com.honeykee.webflux.demo.entity;

import lombok.Data;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/4/14 2:22 下午
 * @since JDK 1.8
 */

@Data
public class City {
    /**
     * 城市编号 */
    private Long id;
    /**
     * 省份编号 */
    private Long provinceId;
    /**
     * 城市名称 */
    private String cityName;
    /**
     * 描述 */
    private String description;
}
