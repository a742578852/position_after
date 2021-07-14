package com.justiceLeague.util;

import com.justiceLeague.model.Coordinate;
import com.justiceLeague.model.EquipmentBeacon;
import com.justiceLeague.model.LocationLog;
import com.justiceLeague.model.Round;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定位核心算法
 */
public class LocationAlgorithm {

    /*所有组合的总权值*/
    private static double totalWeight;


    public static Map evaluateCoordinates(List<EquipmentBeacon> beacons) {
        //实例化结果

        double x;
        double y;
        int floor;
        Map locationMap = new HashMap();
        //一个点的情况
        if (beacons.size() == 1) {
            EquipmentBeacon equipmentBeacon = beacons.get(0);
            locationMap.put("x", equipmentBeacon.getX());
            locationMap.put("y", equipmentBeacon.getY());
            locationMap.put("floor", equipmentBeacon.getFloor());
            return locationMap;
            //两个点的情况
        } else if (beacons.size() == 2) {
            EquipmentBeacon beacon1 = beacons.get(0);
            EquipmentBeacon beacon2 = beacons.get(1);
            double pro;
            double xx;
            double yy;
            if (beacon1.getRssi() > beacon2.getRssi()) {
                //获取两个点的比例
               pro = beacon1.getRssi()/beacon2.getRssi();
               xx = beacon2.getX();
               yy = beacon2.getY();
               floor = beacon2.getFloor();
            } else {
                pro = beacon2.getRssi()/beacon1.getRssi();
                xx = beacon1.getX();
                yy = beacon1.getY();
                floor = beacon1.getFloor();
            }

            if (beacon1.getX() > beacon2.getX()){
                x = xx + ((beacon1.getX() - beacon2.getX())/2.0)*pro;
            }else {
                x = xx + ((beacon2.getX() - beacon1.getX())/2.0)*pro;
            }
            if (beacon1.getY() > beacon2.getY()){
                y = yy + ((beacon1.getY() - beacon2.getY())/2.0)*pro;
            }else {
                y = yy + ((beacon2.getY() - beacon1.getY())/2.0)*pro;
            }
            locationMap.put("x", x);
            locationMap.put("y", y);
            locationMap.put("floor", floor);

            //三个点的情况
        } else {
            //获取三个点
            EquipmentBeacon beacon1 = beacons.get(0);
            EquipmentBeacon beacon2 = beacons.get(1);
            EquipmentBeacon beacon3 = beacons.get(2);

            EquipmentBeacon beMin;
            //求距离最近的坐标
            if (beacon1.getRssi()<beacon2.getRssi()){
                if (beacon1.getRssi()<beacon3.getRssi()){
                    beMin = beacon1;
                }else {
                    beMin = beacon3;
                }
            }else {
                if (beacon2.getRssi()<beacon3.getRssi()){
                    beMin = beacon2;
                }else {
                    beMin = beacon3;
                }
            }

            double rssi1 = GetCoordinate.getDistanceByRiss(beacon1.getRssi());
            double rssi2 = GetCoordinate.getDistanceByRiss(beacon2.getRssi());
            double rssi3 = GetCoordinate.getDistanceByRiss(beacon3.getRssi());

            Round round1 = new Round(beacon1.getX(),beacon1.getY(),rssi1);
            Round round2 = new Round(beacon2.getX(),beacon2.getY(),rssi2);
            Round round3 = new Round(beacon3.getX(),beacon3.getY(),rssi3);
            Coordinate coordinate = GetCoordinate.triCentroid(round1,round2,round3);
            locationMap.put("x", coordinate.getX());
            locationMap.put("y", coordinate.getY());
            locationMap.put("floor", beMin.getFloor());

        }
        return locationMap;


    }


}
