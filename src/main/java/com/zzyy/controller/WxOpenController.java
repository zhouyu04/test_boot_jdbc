package com.zzyy.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzyy.exception.CustomException;
import com.zzyy.service.WxService;
import com.zzyy.utils.wx.WxTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Auther: zhouyu
 * @Date: 2020/6/23 16:40
 * @Description:
 */
@Controller
@RequestMapping("/openapi")
@Slf4j
public class WxOpenController {

    private static Logger logger = LoggerFactory.getLogger(WxOpenController.class);

    @Resource
    WxService wxService;

    /**
     * 功能描述: 跳转微信授权
     *
     * @auther: zhouyu
     * @date: 2020/7/15 10:17
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response) {

        String appId = StringUtils.isBlank(request.getParameter("appId"))
                ? "wxb6051228351f2072" : request.getParameter("appId");
        String username = StringUtils.isBlank(request.getParameter("username"))
                ? "administrator" : request.getParameter("username");
        String dbid = StringUtils.isBlank(request.getParameter("dbid"))
                ? "19002" : request.getParameter("dbid");
        String tenantid = StringUtils.isBlank(request.getParameter("tenantid"))
                ? "jdy" : request.getParameter("tenantid");

        request.setAttribute("appId", appId);
        request.setAttribute("username", username);
        request.setAttribute("dbid", dbid);
        request.setAttribute("tenantid", tenantid);

        String url = String.format("/#/auth?" +
                        "appId=%s&username=%s&dbid=%s&tenantid=%s",
                appId, username, dbid, tenantid);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            logger.error("跳转微信授权失败", e);
        }
    }

    @RequestMapping(value = "/weChat/getUserInfo/{lname}/{uid}", produces = "text/html;charset=UTF-8")
    public void getUserInfo(@PathVariable String lname, @PathVariable long uid,
                            @RequestParam("dbid") String dbid,
                            @RequestParam("tid") String tid,
                            HttpServletRequest request, HttpServletResponse response) {
        try {
            log.info("~~getUserInfo first~~");
            //获取上一个页面传下来的会员信息

            String access = wxService.getAccess(dbid);
            String activate_ticket = request.getParameter("activate_ticket");
            String url = "https://api.weixin.qq.com/card/membercard/activatetempinfo/get?access_token=" + access;

            JSONObject params = new JSONObject();
            params.put("activate_ticket", activate_ticket);

            String res = WxTokenUtils.sendPost(url, params.toJSONString(), new HashMap<>());
            log.info("查询用户信息返回:" + res);
            if (StringUtils.isBlank(res)) {
                //todo 这里需要跳转到异常页面   带上消息
            }

            JSONObject object = JSONObject.parseObject(res);
            if (object.containsKey("errcode") || object.getIntValue("errcode") != 0) {
                //todo 这里也需要跳转到异常页面  带上消息
            }

            JSONObject info = object.getJSONObject("info");
            JSONArray common_field_list = info.getJSONArray("common_field_list");

            JSONObject obj = new JSONObject();

            for (int i = 0; i < common_field_list.size(); i++) {
                JSONObject jsonObject = common_field_list.getJSONObject(i);
                String name = jsonObject.getString("name");
                name = StringUtils.substringAfterLast(StringUtils.lowerCase(name), "_");
                obj.put(name, jsonObject.getString("value"));
            }
            log.info("getUserInfo={}", obj);
            response.sendRedirect(String.format("/#/activate?card_id=%s&mobile=%s&openid=%s&sex=%s&birthday=%s&name=%s&fdbid=%s",
                    request.getParameter("card_id"),
                    obj.getString("mobile"),
                    request.getParameter("openid"),
                    URLEncoder.encode(obj.getString("sex"), "utf-8"),
                    obj.getString("birthday"),
                    URLEncoder.encode(obj.getString("name"), "utf-8"),
                    dbid));

        } catch (IOException e) {
            log.error("getUserInfo", e);
        }
    }


    /**
     * 功能描述: 跳转充值记录
     *
     * @auther: zhouyu
     * @date: 2020/7/15 10:17
     */
    @RequestMapping(value = "/weChat/recharge/{lname}/{uid}", produces = "text/html;charset=UTF-8")
    public void recharge(@PathVariable String lname, @PathVariable long uid, @RequestParam("dbid") long dbid,
                         HttpServletRequest request, HttpServletResponse response) {

        try {
            String url = String.format("/#/rechargeList?" +
                            "card_id=%s&encrypt_code=%s&openidCard=%s&outer_str=%s&fdbid=%s&loginName=%s&uid=%s",
                    request.getParameter("card_id"), request.getParameter("encrypt_code"),
                    request.getParameter("openid"), request.getParameter("outer_str"), dbid, lname, uid);
            log.info("rechargeRecord url={}", url);
            response.sendRedirect(url);
        } catch (IOException e) {
            log.error("查询充值记录失败：" + e.getMessage());
        }

    }

    /**
     * 功能描述: 充值记录查询
     *
     * @auther: zhouyu
     * @date: 2020/7/15 10:17
     */
    @RequestMapping(value = "/recharge/list/{dbid}")
    @ResponseBody
    public JSONObject rechargeList(@PathVariable String dbid, HttpServletRequest request) {

        return wxService.rechargeList(dbid, request);

    }

    /**
     * 功能描述: 跳转消费记录
     *
     * @auther: zhouyu
     * @date: 2020/7/15 10:16
     */
    @RequestMapping(value = "/weChat/retail/{lname}/{uid}", produces = "text/html;charset=UTF-8")
    public void retial(@PathVariable String lname, @PathVariable long uid, @RequestParam("dbid") long dbid,
                       HttpServletRequest request, HttpServletResponse response) {

        try {
            String url = String.format("/#/retailList?" +
                            "card_id=%s&encrypt_code=%s&openidCard=%s&outer_str=%s&fdbid=%s&loginName=%s&uid=%s",
                    request.getParameter("card_id"), request.getParameter("encrypt_code"),
                    request.getParameter("openid"), request.getParameter("outer_str"), dbid, lname, uid);
            log.info("rechargeRecord url={}", url);
            response.sendRedirect(url);
        } catch (IOException e) {
            log.error("查询消费记录记录失败：" + e.getMessage());
        }

    }

    /**
     * 功能描述: 消费记录查询
     *
     * @auther: zhouyu
     * @date: 2020/7/15 10:17
     */
    @RequestMapping(value = "/retail/list/{dbid}")
    @ResponseBody
    public JSONObject retailList(@PathVariable String dbid, HttpServletRequest request) {

        return wxService.retailList(dbid, request);

    }


    /**
     * 功能描述: 老会员绑定跳转
     *
     * @auther: zhouyu
     * @date: 2020/7/15 10:47
     */
    @RequestMapping(value = "/bindOldMember/{lname}/{uid}")
    public void bindOldMember(@PathVariable String lname, @PathVariable long uid, @RequestParam("dbid") long dbid,
                              HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = String.format("/#/bindOldMember?" +
                            "card_id=%s&encrypt_code=%s&openidCard=%s&outer_str=%s&fdbid=%s&loginName=%s&uid=%s",
                    request.getParameter("card_id"), request.getParameter("encrypt_code"),
                    request.getParameter("openid"), request.getParameter("outer_str"), dbid, lname, uid);

            log.info("rechargeRecord url={}", url);
            response.sendRedirect(url);
        } catch (IOException e) {
            log.error("跳转老会员绑定失败：" + e.getMessage());
        }

    }


    @RequestMapping(value = "/weChat/route/{dbid}")
    @ResponseBody
    public JSONObject wechatRoute(@PathVariable String dbid,
                                  HttpServletRequest request, HttpServletResponse response) {

        try {
            return wxService.wechatRoute(dbid, request);
        } catch (Exception e) {
            log.info("转发请求异常", e);
            throw new CustomException("200", "转发请求异常" + e.getMessage());
        }

    }


}
