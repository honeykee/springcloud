package com.honeykee.auth.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-28 11:32
 * @since JDK 1.8
 */

//@Component
@Slf4j
public class AuthenticationProviderConfig implements AuthenticationProvider {

    /**
     * 配置这种方式，在用户校验的时候走这一步，导致不会从usermgmt服务校验用户，直接登陆成功,这种适合用来用户其他校验方式,比如短信验证码，二维码，
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate( Authentication authentication) throws AuthenticationException {
        log.info("自定义provider调用");
        // 返回一个Token对象表示登陆成功
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), Collections.< GrantedAuthority >emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
