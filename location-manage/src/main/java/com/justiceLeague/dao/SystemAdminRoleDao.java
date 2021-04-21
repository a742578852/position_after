package com.justiceLeague.dao;

import com.justiceLeague.model.SystemAdminRole;

public interface SystemAdminRoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(SystemAdminRole record);

    int insertSelective(SystemAdminRole record);

    SystemAdminRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemAdminRole record);

    int updateByPrimaryKey(SystemAdminRole record);
}