package com.justiceLeague.model;

import lombok.Data;

import java.util.Date;

@Data
public class AlertSet {

    private Long id;

    /**
     * 报警类型
     */
    private int alertType;

    /**
     * 0进入 1离开
     */
    private int inout;

    /**
     * 超出时间 单位 m
     */
    private int stayTime;

    /**
     * 超出人员
     */
    private int transcendCount;

    /**
     * 缺少人员
     */
    private int lackCount;

    /**
     * 静止时间 单位 m
     */
    private int staticTime;

    /**
     * 生效星期 1,2,..
     */
    private String effectDay;

    /**
     * 生效起始时间
     */
    private Date effectStartTime;

    /**
     * 生效结束时间
     */
    private Date effectEndTime;

    /**
     * 区域id
     */
    private Long enclosureId;

    /**
     * 创建时间
     */
    private Date createTime;

}
