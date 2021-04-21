package com.justiceLeague.service;

import com.justiceLeague.model.SystemAdmin;
import com.justiceLeague.util.Result;

public interface SystemAdminService {

    /**
     * 根据id查询账号
     * @param id
     * @return
     */
    SystemAdmin getAdminById(long id);

    /**
     * 添加普通账户
     * @return
     */
    Result addAccount(Long personId, String loginName, String passWord);
}
