package com.honeykee.common.utils.mapper;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;




/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-02 15:11
 * @since JDK 1.8
 */

@Slf4j
public class JsonMapper {

    private ObjectMapper mapper;

    public JsonMapper() {
        this(null);
    }

    public JsonMapper( JsonInclude.Include include) {
        mapper = new ObjectMapper();
        // 设置输出时包含属性的风格
        if (include != null) {
            mapper.setSerializationInclusion(include);
        }
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    /**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
     */
    public static JsonMapper nonEmptyMapper() {
        return new JsonMapper( JsonInclude.Include.NON_EMPTY);
    }

    /**
     * 创建只输出非Null属性到Json字符串的Mapper
     */
    public static JsonMapper nonNullMapper() {
        return new JsonMapper( JsonInclude.Include.NON_NULL);
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
     */
    public static JsonMapper nonDefaultMapper() {
        return new JsonMapper( JsonInclude.Include.NON_DEFAULT);
    }



    public String toJson(Object object) {

        try {
            return mapper.writeValueAsString(object);
        } catch ( IOException e) {
            log.warn("write to json string error:" + object, e);
            return null;
        }
    }
}
