package com.honeykee.test.example.model;

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
public class Person {

    private Integer age;
    private String name;
}
