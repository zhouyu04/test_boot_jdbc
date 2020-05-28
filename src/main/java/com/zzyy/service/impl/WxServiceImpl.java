package com.zzyy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zzyy.entity.WxVerifyTicket;
import com.zzyy.exception.CustomException;
import com.zzyy.mapper.WxMapper;
import com.zzyy.utils.wx.WXBizMsgCrypt;
import com.zzyy.utils.wx.WxTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    RedisTemplate redisTemplate;

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

    /**
     * 功能描述: 获取预授权码
     *
     * @auther: zhouyu
     * @date: 2020/5/27 15:51
     */
    @Override
    public String getPreCode() {

        //查询票据
        WxVerifyTicket component_verify_ticket = wxMapper.findByKey(APPID, "component_verify_ticket");

        if (component_verify_ticket == null) {
            throw new CustomException("600", "找不到票据");
        }
        String ticket = component_verify_ticket.getTicket();
        //获取令牌
        Object o = redisTemplate.opsForValue().get("accessToken");
        String token = "";
        if (o == null) {
            token = WxTokenUtils.getAccessToken(ticket);
            JSONObject object = JSONObject.parseObject(token);
            token = object.getString("component_access_token");

            redisTemplate.opsForValue().set("accessToken", token, 60, TimeUnit.SECONDS);
        } else {
            token = (String) o;
        }
        //获取预授权码
        String url = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token="
                + token;

        JSONObject params = new JSONObject();
        params.put("component_appid", APPID);

        String preCode = WxTokenUtils.sendPost(url, params.toJSONString());
        log.info("获取预授权码返回：" + preCode);
        JSONObject object = JSONObject.parseObject(preCode);
        String pre_auth_code = object.getString("pre_auth_code");

        return pre_auth_code;
    }

    @Override
    public void callback(HttpServletRequest request) {

        String dataFromRequst = getDataFromRequst(request);
        String queryString = request.getQueryString();
        log.info("回调参数：dataFromRequst：" + dataFromRequst + "、queryString" + queryString);
        try {
            Document document = DocumentHelper.parseText(dataFromRequst);
            Element rootElement = document.getRootElement();

            WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(TOKEN, KEY, APPID);

            Element encrypt = rootElement.element("Encrypt");
            String encryptStringValue = encrypt.getStringValue();
            String decrypt = wxBizMsgCrypt.decrypt(encryptStringValue);

            log.info("解析后：" + decrypt);

        } catch (Exception e) {
            log.error("解析XML异常", e);
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
