package com.justiceLeague.util;

import com.justiceLeague.model.extem.APInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 三角定位实现类
 */
public class GetCoordinate {

    APInfo apInfo = new APInfo();

    static double totalWeight = 0;

    /**
     *
     * @return
     */
    public static APInfo getApiInfo(List<Map> aps){
        //根据apuid从数据库获取配置信息
        List<APInfo> apInfos = new ArrayList<>();
        APInfo apInfoA = new APInfo();
        apInfoA.setUuid("1");
        apInfoA.setLocationX(10);
        apInfoA.setLocationY(20);
        apInfoA.setRssi(-20);

        APInfo apInfoB = new APInfo();
        apInfoA.setUuid("2");
        apInfoA.setLocationX(20);
        apInfoA.setLocationY(30);
        apInfoA.setRssi(-30);

        APInfo apInfoC = new APInfo();
        apInfoA.setUuid("1");
        apInfoA.setLocationX(30);
        apInfoA.setLocationY(40);
        apInfoA.setRssi(-30);

        apInfos.add(apInfoA);
        apInfos.add(apInfoB);
        apInfos.add(apInfoC);
        //根据Rssi获取ue到ap的水平距离
        for (APInfo apInfo:apInfos){
            double rawDis = 0.0;
            double power = (apInfo.getA() - apInfo.getRssi()) / (10*apInfo.getN());
            rawDis = Math.pow(10,power);
            apInfo.setDistance(Math.sqrt(Math.pow(rawDis, 2) - Math.pow(apInfo.height, 2)));
        }


        //根据三边定位获取ue的具体坐标

    }


    /**
     * 根据信号强度获取距离
     * @return
     */
    public double getDistanceByRiss(double rssi){


        Double Rssi = Math.abs(rssi);
        double power = (Rssi - 60) / (10.0 * 3.3);
        //93=10米    60=1米
        String location=String.valueOf(Math.pow(10, power));
        return Double.parseDouble(location.substring(0,6));
    }




}
