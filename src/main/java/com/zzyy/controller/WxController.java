package com.zzyy.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzyy.interceptor.JResponse;
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
import java.net.URLEncoder;

/**
 * @Auther: zhouyu
 * @Date: 2020/5/25 17:12
 * @Description:
 */
@Controller
@RequestMapping("/wx")
@Slf4j
public class WxController extends BaseController {

    @Resource
    WxService wxService;


    @RequestMapping("/notify/{appId}")
    @ResponseBody
    public JResponse<Object> notify(@PathVariable String appId, HttpServletRequest request) {

        wxService.notify(appId, request);

        return this.result();
    }

    @RequestMapping("/info")
    @ResponseBody
    public JResponse<String> info(HttpServletRequest request) {


        try {
            wxService.saveVerifyTicket(request);
        } catch (Exception e) {
            log.error("转换XML异常：" + e);
            return this.result("fail");
        }

        return this.result("success");
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
    public void getPreCode(HttpServletRequest request, HttpServletResponse response) {

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

        try {
            String url = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid="
                    + WxTokenUtils.APPID
                    + "&pre_auth_code=" + preCode
                    + "&auth_type=1"
                    + "&redirect_uri=http://120.77.156.51/wx/callback/"
                    + appId + "/" + URLEncoder.encode(username, "GBK") + "/" + dbid + "/" + tenantid;
            response.sendRedirect(url);
        } catch (IOException e) {
            log.error("跳转授权页面失败", e);
        }

    }


    @RequestMapping("/getTicket")
    @ResponseBody
    public JResponse<String> getTicket() {

        return this.result(wxService.getTicket());
    }


    /**
     * 功能描述: 收取授权公众号token
     *
     * @auther: zhouyu
     * @date: 2020/6/16 15:42
     */
    @RequestMapping("/access")
    @ResponseBody
    public JResponse<String> getAccess(HttpServletRequest request) {

        String dbid = request.getParameter("dbid");
        return this.result(wxService.getAccess(dbid));
    }

    /**
     * 功能描述: 获取开放平台token
     *
     * @auther: zhouyu
     * @date: 2020/6/16 15:42
     */
    @RequestMapping("/getcomptoken")
    @ResponseBody
    public JResponse<JSONObject> getcomptoken() {

        return this.result(wxService.getcomptoken());
    }

    /**
     * 功能描述: 检测是否已经授权，已授权返回授权信息
     *
     * @auther: zhouyu
     * @date: 2020/6/16 16:21
     */
    @RequestMapping("/checkAuthorize")
    @ResponseBody
    public JResponse<JSONObject> checkAuthorize(HttpServletRequest request) {

        String dbid = request.getParameter("dbid");
        return this.result(wxService.checkAuthorize(dbid));
    }

    /**
     * 功能描述: 取消授权-只是删除数据库数据，并没有调用微信
     *
     * @auther: zhouyu
     * @date: 2020/7/29 17:07
     */
    @RequestMapping("/recall")
    @ResponseBody
    public JResponse<Object> recall(HttpServletRequest request) {

        String dbid = request.getParameter("dbid");
        wxService.recall(dbid);
        return this.result();

    }

}


