package com.honeykee.common.utils.result;

import com.honeykee.common.pojo.module.result.GeneralContentResult;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-13 04:12
 * @since JDK 1.8
 */
public class ResultUtil {

    public static <V> GeneralContentResult<V> buildGeneralContentResult(String resultCode, String resultDesc , V content){
        GeneralContentResult result = new GeneralContentResult( );
        result.setResultCode( resultCode );
        result.setResultDesc( resultDesc );
        result.setResultContent( content );
        return result;
    }
}
