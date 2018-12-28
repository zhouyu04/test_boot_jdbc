package com.zzyy.controller;


import com.zzyy.entity.User;
import com.zzyy.exception.CustomException;
import com.zzyy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;


    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request, HttpServletResponse response) {

        logger.info("login");
        String result = "登录成功";

        //查询用户
        User user = userService.queryUser(username, password);
        if (user == null) {
//            result = "用户不存在";
            response.setStatus(600);
            throw new CustomException("600", "用户不存在");
        }
        request.getSession().setAttribute("session_user", user);
        return result;
    }
}
