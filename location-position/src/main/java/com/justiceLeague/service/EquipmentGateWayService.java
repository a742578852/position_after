package com.justiceLeague.service;

/**
 * 基站管理接口
 */
public interface EquipmentGateWayService {

    //基站上电,修改基站状态
    int updateOnline(int online,String mac);

}
