package com.justiceLeague.model;

import lombok.Data;

import java.util.Date;
@Data
public class User {

    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 生日
     */
    private String brithday;

    /**
     * 定位标签卡mac
     */
    private String mac;

    private Date createTime;



}
