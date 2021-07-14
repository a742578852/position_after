package com.justiceLeague.model;

import lombok.Data;

@Data
public class EquipmentBeacon {

    private Long id;

    private String mac;

    private double x;

    private double y;

    private int floor;

    //信号强度值
    private int rssi;

    //电量
    private double electric;

}
