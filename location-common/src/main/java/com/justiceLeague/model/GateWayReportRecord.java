package com.justiceLeague.model;

import lombok.Data;

import java.util.Date;

@Data
public class GateWayReportRecord {

    private Long id;

    /**
     * 网关mac
     */
    private String mac;

    /**
     * 上报类型
     */
    private int type;

    /**
     * 上报类型描述
     */
    private String typeInfo;

    /**
     * 上报的详细数据
     */
    private String data;

    /**
     * 上报时间
     */
    private Date date;

}
