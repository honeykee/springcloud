package com.honeykee.providerapi.service;

import com.honeykee.common.feign.clients.ConsumerClient;
import com.honeykee.common.pojo.module.customer.Dept;
import com.honeykee.common.pojo.module.result.GeneralContentResult;
import com.honeykee.common.pojo.module.customer.WXUserLoginRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-13 04:07
 * @since JDK 1.8
 */
@Service
public class ProviderService {

    @Autowired
    ConsumerClient consumerClient;

    public GeneralContentResult<Dept> getDept(Integer _id){

        GeneralContentResult<Dept> result =  consumerClient.getDeptByWithId( _id );
        return result;

    }

    public GeneralContentResult< WXUserLoginRecord > getWxUserLoginRecord(){
        GeneralContentResult< WXUserLoginRecord > result = consumerClient.getWxUserLoginRecord();
        if ( result != null ){
            WXUserLoginRecord resultContent = result.getResultContent();
            LocalDateTime loginTime = resultContent.getLoginTime();
            LocalDateTime now = LocalDateTime.now();
            System.out.println( "[zcs] now is before "+ now.isBefore( loginTime ) );
        }
        return result;
    }
}
