package com.honeykee.common.pojo.module.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-12 22:17
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResult {

    private String resultCode;

    private String resultDesc;

}
