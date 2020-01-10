package com.honeykee.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-03 19:39
 * @since JDK 1.8
 *
 * copy from provider-api
 */

@Configuration
@EnableResourceServer //这个是必须的
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true )
//@EnableGlobalAuthentication //上一个注解EnableGlobalMethodSecurity包含该注解
public class ResourceServerConfigurerAdapterConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "lizcloud";


    @Autowired
    private ResourceServerProperties resourceServerProperties;

    private ResourceServerTokenServices myRemoteTokenServices() {
        MyRemoteTokenServices remoteService = new MyRemoteTokenServices();
        remoteService.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
        remoteService.setClientId(resourceServerProperties.getClientId());
        remoteService.setClientSecret(resourceServerProperties.getClientSecret());
        return remoteService;
    }

    @Override
    public void configure( HttpSecurity http ) throws Exception {
        http .authorizeRequests()
                .antMatchers ( "/user/registry","/favor.ico", "/login.html",
                        "/**/**.css", "/**/images/**", "**/**.js","/welcome" ,
                        "/swagger**", "/swagger**/**", "/webjars/**", "/v2/**","/js/**",
                        "/css/**", "/noauth/**","/login","/actuator/**" ,"/hystrix/**","/**")
                .permitAll().anyRequest().authenticated() ;
    }

    @Override
    public void configure( ResourceServerSecurityConfigurer resources) throws Exception {
        //from lizservice common-security 还不知道这个作用，见200页面
//        tokenServices: 定义 Token Service。例如用 ResourceServerTokenservices 类，配置 Token 是如何编码和解码的。
//        如果 Resource Server和 Authorization Server在同一个工程上， 则不需要配 置 tokenServices ，
//        如 果不在同 一 个程序就需要配置 。 也可以用 RemoteTokenServices类，即 Resource Server采用远程授权服务器进行
//        Token解码， 这时也不需要配置 此选 项，本章案例采用此方式。
        resources.tokenServices(myRemoteTokenServices());
        //这是数据库的clientId 的 resource_ids，需要配置，否则 在接口请求时候 Invalid token does not contain resource id (oauth2-resource)
        resources.resourceId( RESOURCE_ID ).stateless( false );
        /**
         * 定义 Token Service。例如用 ResourceServerTokenservices 类，配置 Token 是如何编码和解码的。
         * 如果 Resource Server和 Authorization Server在同一个工程上， 则不需要配 置 tokenServices ，如 果不在同 一 个程序就需要配置 。
         * 也可以用 RemoteTokenServices类，即 Resource Server采用远程授权服务器进行 Token解码，这时也不需要配置此选项,本章案例采用此方式。
         */
//        resources.tokenServices(  );
//        resources.tokenStore(  );
//        resources.authenticationManager(  );
//        resources.accessDeniedHandler(  );

    }

}
