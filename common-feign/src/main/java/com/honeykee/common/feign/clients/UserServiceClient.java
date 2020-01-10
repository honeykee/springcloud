package com.honeykee.common.feign.clients;

import com.honeykee.common.feign.clients.fallback.UserServiceFallback;
import com.honeykee.common.pojo.module.result.GeneralContentResult;
import com.honeykee.common.pojo.module.user.UserPrincipal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-02 17:42
 * @since JDK 1.8
 */
@FeignClient(value = "user-service", fallback = UserServiceFallback.class)//
public interface UserServiceClient {

    @GetMapping( "/noauth/user/get/by/name" )
    GeneralContentResult< UserPrincipal > getUserByUserName( @RequestParam("userName") String _userName );

}
