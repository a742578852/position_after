package com.justiceLeague.service.impl;

import com.justiceLeague.mapper.EquipmentBeaconMapper;
import com.justiceLeague.model.EquipmentBeacon;
import com.justiceLeague.service.EquipmentBeaconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentBeaconServiceImpl implements EquipmentBeaconService {
    @Autowired
    EquipmentBeaconMapper equipmentBeaconMapper;

    @Override
    public EquipmentBeacon getBeacon(String mac) {
        return equipmentBeaconMapper.queryBeaconByMac(mac);
    }
}
