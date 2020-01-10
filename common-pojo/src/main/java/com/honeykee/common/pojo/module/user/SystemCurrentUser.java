package com.honeykee.common.pojo.module.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;


/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-02 17:58
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode( callSuper = false )
public class SystemCurrentUser extends User {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String userName;

//    private List<GrantedAuthority> roles;

    public SystemCurrentUser(String username, String password,
                             boolean enabled, boolean accountNonExpired,
                          boolean credentialsNonExpired, boolean accountNonLocked,
                             Collection<? extends GrantedAuthority > authorities,
                          String userId, String userName ) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.userId = userId;
        this.userName = userName;
    }


}
