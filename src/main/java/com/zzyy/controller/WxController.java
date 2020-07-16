package com.zzyy.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzyy.service.WxService;
import com.zzyy.utils.wx.WxTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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


    @RequestMapping("/notify/{appId}")
    @ResponseBody
    public String notify(@PathVariable String appId, HttpServletRequest request) {

        wxService.notify(appId, request);

        return "";
    }

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


    @RequestMapping("/callback/{appId}/{username}/{dbid}/{tenantid}")
    public void callback(HttpServletRequest request, HttpServletResponse response,
                         @PathVariable String appId,
                         @PathVariable String username,
                         @PathVariable String dbid,
                         @PathVariable String tenantid) {
        request.setAttribute("appId", appId);
        request.setAttribute("username", username);
        request.setAttribute("dbid", dbid);
        request.setAttribute("tenantid", tenantid);

        wxService.callback(request);

        String url = String.format("/#/success");
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            log.error("跳转成功页面失败", e);
        }
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

        JSONObject params = new JSONObject();
        params.put("appId", appId);
        params.put("username", username);
        params.put("dbid", dbid);
        params.put("tenantid", tenantid);

        String url = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid="
                + WxTokenUtils.APPID
                + "&pre_auth_code=" + preCode
                + "&auth_type=1"
                + "&redirect_uri=http://120.77.156.51/wx/callback/"
                + appId + "/" + username + "/" + dbid + "/" + tenantid;
        //appid_username_dbid_tenantid

        return "redirect:" + url;

    }


    @RequestMapping("/getTicket")
    @ResponseBody
    public String getTicket() {

        return wxService.getTicket();
    }


    /**
     * 功能描述: 收取授权公众号token
     *
     * @auther: zhouyu
     * @date: 2020/6/16 15:42
     */
    @RequestMapping("/access")
    @ResponseBody
    public String getAccess(HttpServletRequest request) {

        String dbid = request.getParameter("dbid");
        return wxService.getAccess(dbid);
    }

    /**
     * 功能描述: 获取开放平台token
     *
     * @auther: zhouyu
     * @date: 2020/6/16 15:42
     */
    @RequestMapping("/getcomptoken")
    @ResponseBody
    public JSONObject getcomptoken() {

        return wxService.getcomptoken();
    }

    /**
     * 功能描述: 检测是否已经授权，已授权返回授权信息
     *
     * @auther: zhouyu
     * @date: 2020/6/16 16:21
     */
    @RequestMapping("/checkAuthorize")
    @ResponseBody
    public JSONObject checkAuthorize(HttpServletRequest request) {

        String dbid = request.getParameter("dbid");
        return wxService.checkAuthorize(dbid);
    }

    //解除绑定
    @RequestMapping("/recall")
    @ResponseBody
    public JSONObject recall(HttpServletRequest request) {

        String dbid = request.getParameter("dbid");
        return wxService.recall(dbid);

    }

}


