package com.zzyy.service.impl;

import com.zzyy.entity.WxVerifyTicket;
import com.zzyy.mapper.WxMapper;
import com.zzyy.utils.wx.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: zhouyu
 * @Date: 2020/5/26 19:31
 * @Description:
 */
@Service
@Slf4j
public class WxServiceImpl implements com.zzyy.service.WxService {


    @Resource
    WxMapper wxMapper;

    private static final String TOKEN = "jdyretail$2018";
    private static final String KEY = "91028f2ee1b4f65393b9d97831611b7a1234567890a";
    private static final String APPID = "wxb6051228351f2072";

    @Override
    public void saveVerifyTicket(HttpServletRequest request) throws Exception {

        String dataFromRequst = getDataFromRequst(request);
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();

        String msg_signature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

        log.info("请求URI：" + requestURI + "、URL:" + requestURL.toString() +
                "、queryString:" + queryString);
        log.info("微信验证令牌请求：" + dataFromRequst);


        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(TOKEN, KEY, APPID);

        Document document = DocumentHelper.parseText(dataFromRequst);
        Element rootElement = document.getRootElement();

        Element appId = rootElement.element("AppId");
        String appIdStringValue = appId.getStringValue();

        Element encrypt = rootElement.element("Encrypt");
        String encryptStringValue = encrypt.getStringValue();
        String decrypt = wxBizMsgCrypt.decrypt(encryptStringValue);

        Document documentDecrypt = DocumentHelper.parseText(decrypt);
        Element re = documentDecrypt.getRootElement();


        Element infoType = re.element("InfoType");
        String type = infoType.getStringValue();

        Element componentVerifyTicket = re.element("ComponentVerifyTicket");
        String verifyTicket = componentVerifyTicket.getStringValue();

        //查询是否已经存在
        WxVerifyTicket exist = wxMapper.findByKey(appIdStringValue, type);

        if (exist != null) {
            exist.setTicket(verifyTicket);
            wxMapper.updateVerifyTicket(exist);
        } else {
            WxVerifyTicket vt = new WxVerifyTicket();

            vt.setAppId(appIdStringValue);
            vt.setInfoType(type);
            vt.setTicket(verifyTicket);

            wxMapper.saveVerifyTicket(vt);
        }
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
