package com.honeykee.user.service.controller;

import com.honeykee.common.pojo.module.result.GeneralContentResult;
import com.honeykee.common.pojo.module.user.UserPrincipal;
import com.honeykee.common.utils.result.ResultCode;
import com.honeykee.common.utils.result.ResultDesc;
import com.honeykee.common.utils.result.ResultUtil;
import com.honeykee.user.service.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-01 17:26
 * @since JDK 1.8
 */
@RestController
@Slf4j
@Api(tags = "User-Api服务")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation( value = "getUserByName")
    @GetMapping("/noauth/user/get/by/name")
    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, value = "Token", dataType = "String", defaultValue = "bearer ")
    public GeneralContentResult< UserPrincipal > getUserByUserName( @RequestParam("userName") String _userName,
                                                                    @RequestHeader("Authorization") String token ){
        log.info( "[zcs]get User By UserName :[{}]", token );

        UserPrincipal userPrincipal = userService.getUserByUserName( _userName );
        return ResultUtil.buildGeneralContentResult( ResultCode.SUCCESS, ResultDesc.SUCCESS, userPrincipal );
    }

}
