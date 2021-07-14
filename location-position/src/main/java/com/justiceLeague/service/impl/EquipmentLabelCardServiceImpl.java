package com.justiceLeague.service.impl;

import com.justiceLeague.mapper.EquipmentLabelCardMapper;
import com.justiceLeague.model.EquipmentLabelCard;
import com.justiceLeague.service.EquipmentLabelCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentLabelCardServiceImpl implements EquipmentLabelCardService {

    @Autowired
    EquipmentLabelCardMapper equipmentLabelCardMapper;

    @Override
    public EquipmentLabelCard getLabekCard(String mac) {
        return equipmentLabelCardMapper.queryLabelCardByMac(mac);
    }

    @Override
    public int updateEle(String mac, int ele) {
        return equipmentLabelCardMapper.updateCardEle(mac,ele);
    }
}
