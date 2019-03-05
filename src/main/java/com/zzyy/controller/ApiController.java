package com.zzyy.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class ApiController {

    @RequestMapping("hello")
    public String hello(@RequestParam("name") String name) {

        if (StringUtils.isBlank(name)) {
            return "日你仙人，你不传姓名!";
        }
        String res = "hello" + name;
        return res;
    }
}
