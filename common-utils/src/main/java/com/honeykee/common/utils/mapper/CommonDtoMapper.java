package com.honeykee.common.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-02 14:33
 * @since JDK 1.8
 */

@Repository
@Mapper(componentModel = "spring")
public class CommonDtoMapper {
    public static CommonDtoMapper INSTANCES = Mappers.getMapper( CommonDtoMapper.class );

}
