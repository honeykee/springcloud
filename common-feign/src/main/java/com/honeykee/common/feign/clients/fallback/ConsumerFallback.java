package com.honeykee.common.feign.clients.fallback;

import com.honeykee.common.feign.clients.ConsumerClient;
import com.honeykee.common.pojo.module.customer.Dept;
import com.honeykee.common.pojo.module.customer.WXUserLoginRecord;
import com.honeykee.common.pojo.module.result.GeneralContentResult;
import com.honeykee.common.utils.result.ResultCode;
import com.honeykee.common.utils.result.ResultDesc;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-12 22:12
 * @since JDK 1.8
 */

@Component
@Slf4j
public class ConsumerFallback implements FallbackFactory<ConsumerClient> {

    @Override
    public ConsumerClient create( Throwable cause ) {
        log.error( "[zcs]error:[{}], cause:[{}]", cause.getMessage(), cause );


        return new ConsumerClient() {
            @Override
            public GeneralContentResult<Dept> getDeptByWithId( Integer _id ) {
                log.error( "[zcs]client error...." );
                GeneralContentResult<Dept> result = new GeneralContentResult<>(  );
                result.setResultCode( ResultCode.SERVICE_ERROR );
                result.setResultDesc( ResultDesc.SERVICE_ERROR );
                result.setResultContent( new Dept( 0,"error" ) );
                return result ;
            }

            @Override
            public GeneralContentResult< WXUserLoginRecord > getWxUserLoginRecord() {
                return null;
            }
        };
    }
}
