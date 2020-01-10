package com.honeykee.common.feign.clients;

import com.honeykee.common.feign.clients.fallback.ConsumerFallback;
import com.honeykee.common.pojo.module.customer.Dept;
import com.honeykee.common.pojo.module.customer.WXUserLoginRecord;
import com.honeykee.common.pojo.module.result.GeneralContentResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-12 21:56
 * @since JDK 1.8
 */

@Component
@FeignClient(value ="consumer-core",fallbackFactory = ConsumerFallback.class)
public interface ConsumerClient {

    /**
     * 获取
     * @param _id
     * @return
     */
    @GetMapping("/noauth/get/depart/{_id}")
    GeneralContentResult<Dept> getDeptByWithId( @PathVariable(value = "_id") Integer _id );

    @GetMapping("/noauth/get/record")
    GeneralContentResult< WXUserLoginRecord > getWxUserLoginRecord( );



}
