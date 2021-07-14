package com.justiceLeague.mapper;

import com.justiceLeague.model.EquipmentBeacon;
import org.apache.ibatis.annotations.Select;

public interface EquipmentBeaconMapper {

    /**
     * 根据mac地址查询信标信息
     * @param mac
     * @return
     */
    @Select("select * from equipment_beacon where mac = #{mac}")
    EquipmentBeacon queryBeaconByMac(String mac);
}
