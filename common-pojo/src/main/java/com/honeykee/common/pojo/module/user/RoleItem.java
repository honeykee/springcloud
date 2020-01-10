package com.honeykee.common.pojo.module.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-02 14:58
 * @since JDK 1.8
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleItem implements Serializable {

    private static final long serialVersionUID = 23454632321344435L;

    private Long id;

    private String name;
}
