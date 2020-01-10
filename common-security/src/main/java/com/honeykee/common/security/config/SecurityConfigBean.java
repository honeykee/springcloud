package com.honeykee.common.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-11-05 14:27
 * @since JDK 1.8
 */
@Order(value = 0)
@Configuration
public class SecurityConfigBean {

    /**
     * 配置这个导致以下错误,原因：该配置让修改了security全局密码加密格式，client验证时候数据库保存的{noop}secret 需要加密，
     * 对secret加密 存放数据库不需要{bcrypt}，只要保存：$2a$10$V.hkxrUwPsYoe.MxI1u1AOTzrZNsDMAjlRRLwBnX9qpP6A/pdmoAS 即可
     * 或者采取下面直接返回BCryptPasswordEncoder的类型而不要返回 PasswordEncoder ，则不覆盖security全局的passwordEncoder
     * 另外
     * @see com.honeykee.auth.server.config.AuthorizationServerConfigurerAdapterConfig#configure(org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer)
     * oauthServer.passwordEncoder( passwordEncoder );是配置client的加密方式
     * 和
     * @see WebSecurityConfigurerAdapterConfig#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
     *  auth.userDetailsService( userDetailsService ).passwordEncoder( passwordEncoder ); 是配置user登陆用户的加密方式
     *
     *
     * {
     *     "error": "unauthorized",
     *     "error_description": "Full authentication is required to access this resource"
     * }
     * @return
     */
//    @Bean(value = "bCryptPasswordEncoder")
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean(value = "bCryptPasswordEncoder")
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
