package com.honeykee.common.pojo.module.result;

import lombok.Data;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-12 22:24
 * @since JDK 1.8
 */

@Data
public class PageInfo {
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPage;
    private Integer totalRecords;
}
