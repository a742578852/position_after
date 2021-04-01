package com.justiceLeague.service;

import com.justiceLeague.model.SystemAdmin;

public interface SystemAdminService {

    /**
     * 根据id查询账号
     * @param id
     * @return
     */
    SystemAdmin getAdminById(long id);
}
