package com.justiceLeague.mapper;

import com.justiceLeague.model.EquipmentLabelCard;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EquipmentLabelCardMapper {

    //获取定位卡详情根据mac
    @Select("select * from equipment_labelCard where mac = #{mac}")
    EquipmentLabelCard queryLabelCardByMac(String mac);

    //修改电量
    @Update("update equipment_labelCard set electric = #{ele} where mac = #{mac}")
    int updateCardEle(String mac,int ele);
}
