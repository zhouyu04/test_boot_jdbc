package com.zzyy.controller;

import com.zzyy.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: zhouyu
 * @Date: 2020/5/25 17:12
 * @Description:
 */
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxController {

    @Resource
    WxService wxService;

    @RequestMapping("/callback")
    public String callback(HttpServletRequest request) {


        try {
            wxService.saveVerifyTicket(request);
        } catch (Exception e) {
            log.error("转换XML异常：" + e);
        }

        return "";
    }




}


