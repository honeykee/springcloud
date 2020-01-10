package com.honeykee.common.feign.clients.fallback;

import com.honeykee.common.feign.clients.UserServiceClient;
import com.honeykee.common.pojo.module.result.GeneralContentResult;
import com.honeykee.common.pojo.module.user.UserPrincipal;
import com.honeykee.common.utils.result.ResultCode;
import com.honeykee.common.utils.result.ResultDesc;
import com.honeykee.common.utils.result.ResultUtil;
import org.springframework.stereotype.Component;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-02 17:43
 * @since JDK 1.8
 */
@Component
public class UserServiceFallback implements UserServiceClient {
    @Override
    public GeneralContentResult< UserPrincipal > getUserByUserName( String _userName ) {
        return ResultUtil.buildGeneralContentResult( ResultCode.SERVICE_ERROR, ResultDesc.SERVICE_ERROR, null );
    }
}
