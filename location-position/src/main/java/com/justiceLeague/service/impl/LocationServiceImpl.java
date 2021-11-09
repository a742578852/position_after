package com.justiceLeague.service.impl;

import com.justiceLeague.mapper.LocationLogMapper;
import com.justiceLeague.model.LocationLog;
import com.justiceLeague.service.LocationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationLogService {
    @Autowired
    LocationLogMapper locationLogMapper;

    @Override
    public int addLocationLog(LocationLog locationLog) {
        return locationLogMapper.insertLocationLog(locationLog);
    }

    @Override
    public List<LocationLog> getLocationByFive() {
        return locationLogMapper.getLocationByFive();
    }
}
