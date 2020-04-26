//package com.honeykee.webflux.demo.config;
//
//import com.google.common.collect.Lists;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()) //创建API基本信息
////                .groupName("") //指定分组，对应(/v2/api-docs?group=)
//                .pathMapping("") //base地址，最终会拼接Controller中的地址
//                .select() //控制要暴露的接口
//                .apis(RequestHandlerSelectors.basePackage("com.honeykee.webflux.demo.controller")) //通过指定扫描包暴露接口
//                .paths(PathSelectors.any()) //设置过滤规则暴露接口
//                .build().securitySchemes( Lists.newArrayList(new ApiKey("Token", "Authorization", "header"))).securityContexts( Lists.newArrayList(securityContext()));
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth())
//            .forPaths(PathSelectors.any()).build();
//    }
//
//	private List<SecurityReference> defaultAuth() {
//		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//		authorizationScopes[0] = authorizationScope;
//		return Arrays.asList(new SecurityReference("Bearer", authorizationScopes));
//	}
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("App服务").description("App服务").termsOfServiceUrl("")
//                .contact(new Contact("ZCS", "http://www.honeykee.com", "1888")).version("1.0").license("").licenseUrl("").build();
//    }
//}
