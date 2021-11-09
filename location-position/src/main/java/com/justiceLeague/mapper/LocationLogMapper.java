package com.justiceLeague.mapper;

import com.justiceLeague.model.LocationLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LocationLogMapper {

    /**
     * 添加定位信息
     * @param locationLog
     * @return
     */
    @Insert("insert into location_log (mac,user_name,user_id,x,y,floor,create_time) values (#{mac},#{userName},#{userId},#{x},#{y},#{floor},#{createTime})")
    int insertLocationLog(LocationLog locationLog);

    /**
     * 查询当前时间5分钟内的数据
     * @return
     */
    @Select("select * from location_log where create_time between date_add(now(), interval - 5 minute) and now() ORDER BY create_time DESC")
    List<LocationLog> getLocationByFive();

}
