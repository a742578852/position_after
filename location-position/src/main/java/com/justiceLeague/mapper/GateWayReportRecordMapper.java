package com.justiceLeague.mapper;

import com.justiceLeague.model.GateWayReportRecord;
import org.apache.ibatis.annotations.Insert;

public interface GateWayReportRecordMapper {

    /**
     * 保存上报数据
     */
    @Insert("insert into gateway_report_record (mac,type,type_info,data,date) values (#{mac},#{type},#{typeInfo},#{data},#{date})")
    int insertReport(GateWayReportRecord gateWayReportRecord);
}
