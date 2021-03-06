package com.honeykee.common.pojo.module.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-12 22:20
 * @since JDK 1.8
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( callSuper = false )
public class
GeneralContentResult<T> extends GeneralResult {
    private T resultContent;
}
