package com.justiceLeague.model;

import lombok.Data;

@Data
public class EquipmentGateway {

    private Long id;

    /**
     * 网关mac地址
     */
    private String mac;

    /**
     * 是否在线
     */
    private int online;

    private double x;

    private double y;

    private double z;
}
