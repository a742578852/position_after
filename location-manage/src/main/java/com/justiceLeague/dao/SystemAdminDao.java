package com.justiceLeague.dao;


import com.justiceLeague.model.SystemAdmin;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemAdminDao {
    int deleteByPrimaryKey(Long id);

    int insert(SystemAdmin record);

    int insertSelective(SystemAdmin record);

    SystemAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemAdmin record);

    int updateByPrimaryKey(SystemAdmin record);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    SystemAdmin getAdminByUsername(String userName);
}