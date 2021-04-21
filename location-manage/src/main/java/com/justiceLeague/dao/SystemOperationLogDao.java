package com.justiceLeague.dao;

import com.justiceLeague.model.SystemOperationLog;

public interface SystemOperationLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(SystemOperationLog record);

    int insertSelective(SystemOperationLog record);

    SystemOperationLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemOperationLog record);

    int updateByPrimaryKey(SystemOperationLog record);
}