package com.justiceLeague.model;

import lombok.Data;

import java.util.Date;

@Data
public class Enclosure {

    private Long id;

    /**
     * 区域顶点集合
     */
    private String points;

    /**
     * 区域名
     */
    private String areaName;

    /**
     * 是否开启报警
     */
    private int ifAlert;

    /**
     * 创建时间
     */
    private Date createTime;


}
