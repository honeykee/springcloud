package com.honeykee.common.security.service;

import com.honeykee.common.feign.clients.UserServiceClient;
import com.honeykee.common.pojo.module.result.GeneralContentResult;
import com.honeykee.common.pojo.module.user.SystemCurrentUser;
import com.honeykee.common.pojo.module.user.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-01 16:47
 * @since JDK 1.8
 */
@Service(value = "commonUserDetailsService")
@Slf4j
public class CommonUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {

        Long start = System.currentTimeMillis();
        GeneralContentResult< UserPrincipal > response = userServiceClient.getUserByUserName( username );

        log.info("The response from usermgmt is {}.", response);
        UserPrincipal userPrincipal = Optional.ofNullable(response.getResultContent())
                .orElseThrow(() -> new UsernameNotFoundException( username));

//        List<Byte> roleTypes = new ArrayList<>();
        List< GrantedAuthority > authorities = new ArrayList<>();
        userPrincipal.getRoles().forEach(roleItem -> {
            authorities.add(new SimpleGrantedAuthority(roleItem.getName()));
//            roleTypes.add(roleItem.getType());
        });

        SystemCurrentUser currentUser = new SystemCurrentUser(
                username,
                userPrincipal.getPassword(),
                true,
                true,
                true,
                true,
                authorities,
                userPrincipal.getUserId(),
                userPrincipal.getUsername() );
        log.info("Load user info {} ", currentUser);
        long time = System.currentTimeMillis() - start;
        if(time > 100) {
            log.warn("run CustomUserDetailsService-loadUserByUsername time:::{}", time);
        }
        return currentUser;
    }
}

