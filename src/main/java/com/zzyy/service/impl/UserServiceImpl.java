package com.zzyy.service.impl;

import com.zzyy.entity.User;
import com.zzyy.mapper.UserMapper;
import com.zzyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;


    @Override
    public User queryUser(String username, String password) {

        return userMapper.queryUser(username,password);
    }
}
