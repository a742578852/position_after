package com.justiceLeague.service;

import com.justiceLeague.model.LocationLog;

public interface LocationLogService {

    /**
     * 添加定位数据
     * @param locationLog
     * @return
     */
    int addLocationLog(LocationLog locationLog);
}
