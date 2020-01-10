//package com.honeykee.providerapi.config;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author zhang chuan sheng
// * @version 1.0
// * @date 2019-11-05 18:55
// * @since JDK 1.8
// * 给requestTemplate加header用于feign请求consumer-core的api，这种自己定义的方式不需要了
// */
////@Configuration
//public class MyFeignOauth2RequestInterceptor implements RequestInterceptor {
//    private final String AUTHORIZATION_HEADER = "Authorization";
//    private final String BEARER_TOKEN_TYPE = "Bearer";
//
//    @Override
//    public void apply( RequestTemplate requestTemplate) {
//        //方法一
////        ServletRequestAttributes attributes = ( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes();
////        HttpServletRequest request = attributes.getRequest();
////        requestTemplate.header("Token", request.getHeader("Token"));
//        //方法二
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails ) {
//            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
//            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue()));
//        }
//
//    }
//}
