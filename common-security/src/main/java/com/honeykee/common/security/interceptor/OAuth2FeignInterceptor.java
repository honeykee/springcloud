package com.honeykee.common.security.interceptor;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.io.IOException;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-11-05 16:20
 * @since JDK 1.8
 */

@Configuration
@Slf4j
public class OAuth2FeignInterceptor {

//    @Bean
//    public RequestInterceptor getOAuth2RequestInterceptor() throws IOException {
//        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(),);
//    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor( OAuth2ClientContext oAuth2ClientContext,
                                                             OAuth2ProtectedResourceDetails resource) {
        log.info( "[zcs]regist OAuth2FeignInterceptor..." );
        return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, resource);
    }

    @Bean
    public OAuth2RestTemplate clientCredentialsRestTemplate( OAuth2ClientContext oAuth2ClientContext,
                                                             OAuth2ProtectedResourceDetails resource  ) {
        // 最后注入了 一个用 于 向 Uaa 服务请 求的 0Auth2RestTemplate类型的 Bean。
//        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
        return new OAuth2RestTemplate( resource, oAuth2ClientContext );
    }
}
