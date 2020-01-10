package com.honeykee.consumer.controller;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-12 21:44
 * @since JDK 1.8
 */

import com.honeykee.common.pojo.module.customer.Dept;
import com.honeykee.common.pojo.module.result.GeneralContentResult;
import com.honeykee.common.utils.result.ResultCode;
import com.honeykee.common.utils.result.ResultDesc;
import com.honeykee.common.utils.result.ResultUtil;
import com.honeykee.consumer.pojo.WXUserLoginRecord;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "Customer-core")
public class TaskController {


    @ApiOperation( value = "get Dept ...")
    @GetMapping("/noauth/get/depart/{_id}")
    @HystrixCommand(fallbackMethod = "getDeptByIdFallback")
    public GeneralContentResult getDeptById( @PathVariable(value = "_id") Integer _id ){
        Dept dept = new Dept().setId( _id ).setName( "张川胜" );
        GeneralContentResult result = new GeneralContentResult();
        result.setResultCode( ResultCode.SUCCESS );
        result.setResultDesc( ResultDesc.SUCCESS );
        result.setResultContent( dept );

        log.info( "1-1-getDept.. dept:{} ", dept );
        return result;
    }

    public GeneralContentResult getDeptByIdFallback( @PathVariable(value = "_id") Integer _id ){
        Dept dept = new Dept().setId( _id ).setName( "该Id没有部门信息" );

        GeneralContentResult result = new GeneralContentResult();
        result.setResultCode( ResultCode.ERROR );
        result.setResultDesc( ResultDesc.ERROR );
        result.setResultContent( dept );

        log.info( "1-2-getDept.. dept:{} ", dept );
        return result;
    }

    @ApiOperation( "tableB or JPA 查询 " )
    public GeneralContentResult getJpaOrTable(){
        return null;
    }

    @ApiOperation( value = "测试LocalDateTime")
    @GetMapping("/noauth/get/record")
    public GeneralContentResult< WXUserLoginRecord > getWxUserLoginRecord(){
        WXUserLoginRecord wxUserLoginRecord = new WXUserLoginRecord();
        wxUserLoginRecord.setLoginTime( "2019-11-11T11:11:11" );
        return ResultUtil.buildGeneralContentResult( ResultCode.SUCCESS,"success" , wxUserLoginRecord );

    }


}
