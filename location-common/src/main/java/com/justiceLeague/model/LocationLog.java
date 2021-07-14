package com.justiceLeague.model;

import lombok.Data;

import java.util.Date;
@Data
public class LocationLog {
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    private String mac;

    /**
     * 用户id
     */
    private Long userId;

    private double x;

    private double y;

    /**
     * 楼层
     */
    private int floor;

    private Date createTime;


}
