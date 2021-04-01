package com.justiceLeague.service.impl;


import com.justiceLeague.dao.SystemAdminDao;
import com.justiceLeague.model.SystemAdmin;
import com.justiceLeague.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {
    @Autowired
    SystemAdminDao systemAdminDao;
    @Override
    public SystemAdmin getAdminById(long id) {
        return systemAdminDao.selectByPrimaryKey(id);
    }
}
