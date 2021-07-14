package com.justiceLeague.mapper;

import org.apache.ibatis.annotations.Update;

public interface EquipmentGateWayMapper {

    /**
     * 修改基站在线状态
     * @param online
     * @param mac
     * @return
     */
    @Update("update equipment_gateway set online = #{online} where mac = #{mac}")
    int updateGateWayOnline(int online,String mac);

}
