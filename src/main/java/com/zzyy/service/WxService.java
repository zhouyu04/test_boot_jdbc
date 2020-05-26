package com.zzyy.service;

import org.dom4j.DocumentException;

/**
 * @Auther: zhouyu
 * @Date: 2020/5/26 19:31
 * @Description:
 */
public interface WxService {
    void saveVerifyTicket(String dataFromRequst) throws DocumentException;
}
