package com.zzyy.service;

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
}
