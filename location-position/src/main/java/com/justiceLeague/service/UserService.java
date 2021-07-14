package com.justiceLeague.service;

import com.justiceLeague.model.User;

public interface UserService {

    /**
     * 根据定位卡获取用户信息
     * @param mac
     * @return
     */
    User getUser(String mac);
}
