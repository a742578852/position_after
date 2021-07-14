package com.justiceLeague.service;

import com.justiceLeague.model.EquipmentBeacon;

/**
 * 信标管理接口
 */
public interface EquipmentBeaconService {

    /**
     * 获取beacon的详细信息
     * @param mac
     * @return
     */
    EquipmentBeacon getBeacon(String mac);


}
