package com.zzyy.controller;

import com.zzyy.interceptor.SessionInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String toLogin(){

        logger.info("toLogin");
        return "login";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){

        logger.info("index");
        return "index";
    }
}
