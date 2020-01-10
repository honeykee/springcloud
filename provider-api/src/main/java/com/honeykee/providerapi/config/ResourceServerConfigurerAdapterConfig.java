//package com.honeykee.providerapi.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
///**
// * @author zhang chuan sheng
// * @version 1.0
// * @date 2019-09-03 19:39
// * @since JDK 1.8
// * 迁移到common-security
// */
//
//@Configuration
//@EnableResourceServer //这个是必须的
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true )
////@EnableGlobalAuthentication
//public class ResourceServerConfigurerAdapterConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure( HttpSecurity http ) throws Exception {
//        http .authorizeRequests()
//                .antMatchers ( "/user/registry","/favor.ico", "/login.html",
//                        "/**/**.css", "/**/images/**", "**/**.js","/welcome" ,
//                        "/swagger**", "/swagger**/**", "/webjars/**", "/v2/**","/js/**",
//                        "/css/**", "/noauth/**","/login","/actuator/**" ,"/hystrix/**","/**")
//                .permitAll().anyRequest().authenticated() ;
//    }
//
//    @Override
//    public void configure( ResourceServerSecurityConfigurer resources) throws Exception {
//        //这是数据库的clientId 的 resource_ids，需要配置，否则 在接口请求时候 Invalid token does not contain resource id (oauth2-resource)
//        resources.resourceId( "lizcloud" ).stateless( false );
//        /**
//         * 定义 Token Service。例如用 ResourceServerTokenservices 类，配置 Token 是如何编码和解码的。
//         * 如果 Resource Server和 Authorization Server在同一个工程上， 则不需要配 置 tokenServices ，如 果不在同 一 个程序就需要配置 。
//         * 也可以用 RemoteTokenServices类，即 Resource Server采用远程授权服务器进行 Token解码，这时也不需要配置此选项,本章案例采用此方式。
//         */
////        resources.tokenServices(  );
////        resources.tokenStore(  );
////        resources.authenticationManager(  );
////        resources.accessDeniedHandler(  );
//
//    }
//
//}
