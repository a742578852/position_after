package com.justiceLeague.service;

import com.justiceLeague.model.LocationLog;

import java.util.List;

public interface LocationLogService {

    /**
     * 添加定位数据
     * @param locationLog
     * @return
     */
    int addLocationLog(LocationLog locationLog);

    /**
     * 查询当前5分钟内的数据
     * @return
     */
    List<LocationLog> getLocationByFive();
}
