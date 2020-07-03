package com.zzyy.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzyy.exception.CustomException;
import com.zzyy.service.WxService;
import com.zzyy.utils.wx.WxTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Resource
    WxService wxService;

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
            response.sendRedirect(String.format("/index.html?card_id=%s&mobile=%s&openid=%s&sex=%s&birthday=%s&name=%s&fdbid=%s",
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
