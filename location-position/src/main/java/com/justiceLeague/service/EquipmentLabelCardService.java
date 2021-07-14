package com.justiceLeague.service;

import com.justiceLeague.model.EquipmentLabelCard;

/**
 * 定位标签卡管理类
 */
public interface EquipmentLabelCardService {

    /**
     * 获取定位标签详情
     * @param mac
     * @return
     */
    EquipmentLabelCard getLabekCard(String mac);

    /**
     * 修改电量
     * @param mac
     * @param ele
     * @return
     */
    int updateEle(String mac,int ele);
}
