package com.honeykee.zuul.gateway.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-28 15:16
 * @since JDK 1.8
 */

@Configuration
@EnableOAuth2Sso
public class WebSecurityConfigurerAdapterConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure( HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/login", "/client/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable();
    }
}
