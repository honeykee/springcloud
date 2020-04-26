package com.honeykee.test.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-10-29 14:59
 * @since JDK 1.8
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "person-value", description = "descc")
public class Person {

    @ApiModelProperty(value = "age")
    private Integer age;

    @ApiModelProperty(value = "name")
    private String name;
}
