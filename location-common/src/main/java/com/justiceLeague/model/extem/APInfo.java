package com.justiceLeague.model.extem;

import lombok.Data;

@Data
public class APInfo {

    public String uuid;
    public double locationX;
    public double locationY;
    public int rssi;
    public int A = 46; //A为距离探测设备1m时的rssi值的绝对值，最佳范围在45-49之间
    public double n = 4; //n为环境衰减因子，需要测试矫正，最佳范围在3.25-4.5之间
    public double distance;
    public double height;

    private static final long serialVersionUID = 1L;


}
