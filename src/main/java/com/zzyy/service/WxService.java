package com.zzyy.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zhouyu
 * @Date: 2020/5/26 19:31
 * @Description:
 */
public interface WxService {
    void saveVerifyTicket(HttpServletRequest request) throws Exception;

    String getPreCode();

    void callback(HttpServletRequest request);

    String getTicket();

    String getAccess(String dbid);

    JSONObject getcomptoken();

    JSONObject checkAuthorize(String dbid);

    void recall(String dbid);

    void notify(String appId, HttpServletRequest request);

    JSONObject wechatRoute(String dbid, HttpServletRequest request) throws Exception;

    JSONObject rechargeList(String dbid, HttpServletRequest request);

    JSONObject retailList(String dbid, HttpServletRequest request);
}
