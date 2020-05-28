package com.zzyy.controller;

import com.zzyy.service.WxService;
import com.zzyy.utils.wx.WxTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zhouyu
 * @Date: 2020/5/25 17:12
 * @Description:
 */
@Controller
@RequestMapping("/wx")
@Slf4j
public class WxController {

    @Resource
    WxService wxService;

    @RequestMapping("/info")
    @ResponseBody
    public String info(HttpServletRequest request) {


        try {
            wxService.saveVerifyTicket(request);
        } catch (Exception e) {
            log.error("转换XML异常：" + e);
        }

        return "success";
    }


    @RequestMapping("/callback")
    public String callback(HttpServletRequest request) {

        wxService.callback(request);
        return "";
    }


    @RequestMapping("/getPreCode")
    public String getPreCode() {

        String preCode = wxService.getPreCode();

        String url = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid="
                + WxTokenUtils.APPID
                + "&pre_auth_code=" + preCode
                + "&redirect_uri=http://120.77.156.51/user/toLogin"
                + "&auth_type=1";

        return "redirect:" + url;

    }


}


