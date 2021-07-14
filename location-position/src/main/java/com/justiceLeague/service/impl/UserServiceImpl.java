package com.justiceLeague.service.impl;

import com.justiceLeague.mapper.UserMapper;
import com.justiceLeague.model.User;
import com.justiceLeague.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public User getUser(String mac) {

        return userMapper.getUserByMac(mac);
    }
}
