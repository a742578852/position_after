package com.justiceLeague.service.impl;


import com.justiceLeague.dao.SystemAdminDao;
import com.justiceLeague.model.SystemAdmin;
import com.justiceLeague.service.SystemAdminService;
import com.justiceLeague.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class SystemAdminServiceImpl implements SystemAdminService {
    @Autowired
    SystemAdminDao systemAdminDao;
    @Override
    public SystemAdmin getAdminById(long id) {
        return systemAdminDao.selectByPrimaryKey(id);
    }

    @Override
    public Result addAccount(Long personId, String loginName, String passWord) {
        //判断该账号是否存在数据库
        if (systemAdminDao.getAdminByUsername(loginName) != null){
            return new Result(201,true,"当前账号名已被注册,请更换账号名");
        }
        SystemAdmin systemAdmin = new SystemAdmin();
        systemAdmin.setCreateTime(new Date());
        //对明文密码进行加密
        String md5Password = DigestUtils.md5DigestAsHex((passWord+loginName).getBytes());
        systemAdmin.setUserName(loginName);
        systemAdmin.setPassword(md5Password);
        systemAdmin.setPersonId(personId);
        return new Result(200,true,"添加成功");
    }
}
