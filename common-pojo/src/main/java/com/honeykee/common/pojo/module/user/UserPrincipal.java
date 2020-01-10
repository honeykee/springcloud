package com.honeykee.common.pojo.module.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-02 14:45
 * @since JDK 1.8
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements Serializable, Principal {

    private static final long serialVersionUID = 1213615895750236289L;

    private String userId;

    private String password;

    private String username;

    private List<RoleItem> roles;

    @Override
    public String getName() {
        return this.username;
    }
}
