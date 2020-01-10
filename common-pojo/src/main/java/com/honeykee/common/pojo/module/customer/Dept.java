package com.honeykee.common.pojo.module.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-12 21:49
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "部门实体")
public class Dept {
    @ApiModelProperty(example = "部门Id ：133")
    Integer id;
    @ApiModelProperty(example = "部门名称")
    String  name;
}
