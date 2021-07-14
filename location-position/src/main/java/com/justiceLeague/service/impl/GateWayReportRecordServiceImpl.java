package com.justiceLeague.service.impl;

import com.justiceLeague.mapper.GateWayReportRecordMapper;
import com.justiceLeague.model.GateWayReportRecord;
import com.justiceLeague.service.GatewayReportRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GateWayReportRecordServiceImpl implements GatewayReportRecordService {
    @Autowired
    GateWayReportRecordMapper gateWayReportRecordMapper;

    @Override
    public int insertReportRecord(GateWayReportRecord gateWayReportRecord) {
        return gateWayReportRecordMapper.insertReport(gateWayReportRecord);
    }
}
