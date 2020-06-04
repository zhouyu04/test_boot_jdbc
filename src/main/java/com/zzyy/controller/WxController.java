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
        return "index";
    }


    @RequestMapping("/getPreCode")
    public String getPreCode(HttpServletRequest request) {

        String preCode = wxService.getPreCode();

        String queryString = request.getQueryString();
        log.info("queryString:" + queryString);


        String appId = request.getParameter("appid");
        String username = request.getParameter("username");
        String dbid = request.getParameter("dbid");
        String tenantid = request.getParameter("tenantid");

        String url = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid="
                + WxTokenUtils.APPID
                + "&pre_auth_code=" + preCode
                + "&auth_type=1"
                + "&redirect_uri=http://120.77.156.51/wx/callback?appId="
                + appId + "_" + username + "_" + dbid + "_" + tenantid;
        //appid_username_dbid_tenantid

        return "redirect:" + url;

    }


    @RequestMapping("/getTicket")
    @ResponseBody
    public String getTicket() {

        return wxService.getTicket();
    }


}


