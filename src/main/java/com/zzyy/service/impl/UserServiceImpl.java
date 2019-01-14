package com.zzyy.service.impl;

import com.zzyy.entity.User;
import com.zzyy.mapper.UserMapper;
import com.zzyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public User queryUser(String username, String password) {
        //dshdahjkhkshdja12323212121321
        User user = userMapper.queryUser(username, password);
        redisTemplate.opsForList().leftPush("user",user.toString());
        return user;
    }
}
