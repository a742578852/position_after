package com.justiceLeague.service;

import com.justiceLeague.model.GateWayReportRecord;

public interface GatewayReportRecordService {

    /**
     * 添加网关上报数据
     * @return
     */
    int insertReportRecord(GateWayReportRecord gateWayReportRecord);

}
