package com.justiceLeague.service.impl;

import com.justiceLeague.mapper.EquipmentGateWayMapper;
import com.justiceLeague.service.EquipmentGateWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentGateWayServiceImpl implements EquipmentGateWayService {
    @Autowired
    EquipmentGateWayMapper equipmentGateWayMapper;

    @Override
    public int updateOnline(int online,String mac) {
        return equipmentGateWayMapper.updateGateWayOnline(online,mac);
    }
}
