package com.justiceLeague.mapper;

import com.justiceLeague.model.LocationLog;
import org.apache.ibatis.annotations.Insert;

public interface LocationLogMapper {

    /**
     * 添加定位信息
     * @param locationLog
     * @return
     */
    @Insert("insert into location_log (mac,user_name,user_id,x,y,floor,create_time) values (#{mac},#{userName},#{userId},#{x},#{y},#{floor},#{createTime})")
    int insertLocationLog(LocationLog locationLog);

}
