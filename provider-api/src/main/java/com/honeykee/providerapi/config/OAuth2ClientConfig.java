package com.honeykee.providerapi.config;

import com.honeykee.providerapi.pojo.TestBean;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Created by forezp on 2017/5/31.
 *
 * 0Auth2 Client (客户端)用于访问被0Auth2保护起来的资源。客户端括要提供用于存储用户的授权码和访问令牌的机制，需要配置如下两个选项。
 * 1.Protected Resource Configuration (受保护资源配置〉。
 * 2.ClientConfiguration (客户端配置〉。
 *
 * Protected Resource Configuration
 * 使用 OAuth2ProtectedResourceDetails 类型的 Bean 来定义受保护的资源，受保护的资源具 有以下属性。
 *
 *
 */

//@Order(value = 1 )
//@EnableOAuth2Client
//@EnableConfigurationProperties
//@Configuration
@Slf4j
public class OAuth2ClientConfig {


//    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        /**
         * 1. Id: 资源的 Id，它在 Spring 0Auth2 协议中 没有用到，用于客户端寻找资源 ， 不需要 做配置，默认即可 。
         * 2. clientId: 0Auth2 Client 的 Id，和之前 0Auth2 Provider 中配置的一一对应。
         * 3. clientSecret: 客户端密码 ，和之前 0Auth2 Provider 中配置的一一对应。
         * 4. accessTokenUri: 获取 Token 的 API 节点。
         * 5. scope: 客户端 的域 。
         * 6. clientAuthenticationScheme: 有两种客户端验证类型，分别为 Http Basic 和 Form， 默 认为 HttpBasic。
         * 7. userAuthorizationUri:如果用 户需要授权访问资源， 则用户将被重定向到的认证 Uri。
         */
        ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
        return clientCredentialsResourceDetails;
    }

    /**
     *  @EnableOAuth2Client.
     * 1.oauth2ClientContextFilter
     * 2.AccessTokenRequest
     * 这种方式不回得到OAuth2ClientContext上下文信息，和OAuth2ProtectedResourceDetails资源信息，使用下面的方式
     */
//    @Bean("requestInterceptor2")
//    public RequestInterceptor oauth2FeignRequestInterceptor(){
//        return new OAuth2FeignRequestInterceptor( new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
//    }

    /**
     * 该bean迁移到了common-security
     * @param oAuth2ClientContext 注入类OAuth2ClientConfiguration，OAuth2RestOperationsConfiguration两个
     * @param resource
     * @returnOAuth2FeignInterceptor
     */
//    @Bean
//    public RequestInterceptor oauth2FeignRequestInterceptor( OAuth2ClientContext oAuth2ClientContext,
//                                                             OAuth2ProtectedResourceDetails resource ) {
//        log.info( "[zcs]register OAuth2FeignInterceptor..." );
//        return new OAuth2FeignRequestInterceptor( oAuth2ClientContext , resource );
//    }


    /**
     * 该bean迁移到了common-security
     * @param oAuth2ClientContext
     * @param resource
     * @return
     */
//    @Bean
//    public OAuth2RestTemplate clientCredentialsRestTemplate(OAuth2ClientContext oAuth2ClientContext,
//                                                            OAuth2ProtectedResourceDetails resource  ) {
//        // 最后注入了 一个用 于 向 Uaa 服务请 求的 0Auth2RestTemplate类型的 Bean。
////        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
//        return new OAuth2RestTemplate( resource, oAuth2ClientContext );
//    }
}
