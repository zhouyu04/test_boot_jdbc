package com.zzyy.service;

import com.zzyy.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User queryUser(String username, String password);
}
