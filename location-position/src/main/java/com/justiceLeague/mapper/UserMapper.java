package com.justiceLeague.mapper;

import com.justiceLeague.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /**
     * 根据定位卡查询用户信息
     * @param mac
     * @return
     */
    @Select("select * from user where mac = #{mac}")
    User getUserByMac(String mac);

}
