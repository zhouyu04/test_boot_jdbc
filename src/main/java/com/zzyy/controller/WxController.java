package com.zzyy.controller;

import com.zzyy.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
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

        String dataFromRequst = getDataFromRequst(request);
        log.info("微信验证令牌请求：" + dataFromRequst);

        try {
            wxService.saveVerifyTicket(dataFromRequst);
        } catch (DocumentException e) {
            log.error("转换XML异常：" + e);
        }

        return "";
    }


    public static String getDataFromRequst(HttpServletRequest request) {
        try {
            InputStream is = request.getInputStream();
            byte[] data = new byte[10240];
            int len = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = is.read(data)) != -1) {
                baos.write(data, 0, len);
            }
            baos.close();
            baos.flush();
            is.close();
            if (baos.size() > 0) {
                return baos.toString();
            }
        } catch (IOException e) {
            log.error("解析XML异常：", e);
        }
        return null;
    }

}


