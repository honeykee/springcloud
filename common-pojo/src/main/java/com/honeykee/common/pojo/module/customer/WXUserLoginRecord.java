package com.honeykee.common.pojo.module.customer;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class WXUserLoginRecord implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
    private String wxUserId;
    private String agent;
    /**
     * 0 获取二维码 1 等待扫描 2 等待确认 3 登录已确认 4 登录成功 5 登录失败 6 退出
     */
    private Byte status;
    private Byte type;
    private String url;
    private String relateId;
    private String creatorId;
    private String tenantId;
    private LocalDateTime loginTime;
    private String loginOutTime;
    private String createDate;
    private String updateDate;
    private String name;

    /**
     * 当日波动次数
     */
    private Integer outNum;
    /**
     * 当日波动累计时间
     */
    private Long outTime;

}
