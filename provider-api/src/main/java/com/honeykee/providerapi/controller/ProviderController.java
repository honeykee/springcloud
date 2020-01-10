package com.honeykee.providerapi.controller;

import com.honeykee.common.pojo.module.customer.Dept;
import com.honeykee.common.pojo.module.result.GeneralContentResult;
import com.honeykee.common.utils.result.ResultCode;
import com.honeykee.common.utils.result.ResultDesc;
import com.honeykee.common.utils.result.ResultUtil;
import com.honeykee.providerapi.pojo.TestBean;
import com.honeykee.common.pojo.module.customer.WXUserLoginRecord;
import com.honeykee.providerapi.service.ProviderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author gemii
 */
@Api("Provider Api")
@RestController
@Slf4j
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @Autowired
    private TestBean testBean;

//    @Autowired
//    private ClientCredentialsResourceDetails clientCredentialsResourceDetails;

    @ApiOperation( "get Dept ..." )
    @GetMapping("/noauth/dept/{_id}")
    @HystrixCommand(fallbackMethod = "getDeptByIdFallback")
    public GeneralContentResult< Dept > getDeptTest(@PathVariable("_id") Integer _id ){

        log.info( "[zcs]prefix method ...AAAA" );
        GeneralContentResult<Dept> result = providerService.getDept( _id );
        log.info( "[zcs]get dept :{}" , result );

        return result;
    }

    @ApiOperation( "get Dept ..." )
    @GetMapping("/auth/dept/{_id}")
    @HystrixCommand(fallbackMethod = "getDeptByIdFallback")
    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, value = "Token", dataType = "String", defaultValue = "bearer ")
    public GeneralContentResult< Dept > getDept(@PathVariable("_id") Integer _id ){

        GeneralContentResult<Dept> result = providerService.getDept( _id );
        log.info( "2-1-get dept :{}" , result );

        return result;
    }

    @ApiOperation( "get Product ..." )
    @GetMapping("/auth/product/{id}")
    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, value = "Token", dataType = "String", defaultValue = "bearer ")
    public String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails ){
            String username = ( ( UserDetails ) principal ).getUsername();
        }else{
            String username = principal.toString();
        }
        return "product id : " + id;
    }

    @ApiOperation( "get Order ..." )
    @GetMapping("/noauth/order/{id}")
//    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, value = "Token", dataType = "String", defaultValue = "bearer ")
    public String getOrder(@PathVariable String id) {
        //for debug
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return id + " test";
    }



    public GeneralContentResult getDeptByIdFallback( @PathVariable(value = "_id") Integer _id ){
        Dept dept = new Dept().setId( _id ).setName( "Error---" );

        GeneralContentResult result = new GeneralContentResult();
        result.setResultCode( ResultCode.ERROR );
        result.setResultDesc( ResultDesc.ERROR );
        result.setResultContent( dept );

        log.info( "2-2-getDept.. dept:{} ", dept );
        return result;
    }



    @ApiOperation( value = "provider测试LocalDateTime")
    @GetMapping("/noauth/get/record")
    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, value = "Token", dataType = "String", defaultValue = "bearer ")
    public GeneralContentResult< WXUserLoginRecord > getWxUserLoginRecord(){
        GeneralContentResult<WXUserLoginRecord> result = providerService.getWxUserLoginRecord();
        return result ;
    }
}
