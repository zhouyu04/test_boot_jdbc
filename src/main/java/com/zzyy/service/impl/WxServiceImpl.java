package com.zzyy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zzyy.entity.WxVerifyTicket;
import com.zzyy.exception.CustomException;
import com.zzyy.mapper.WxMapper;
import com.zzyy.utils.SHA1;
import com.zzyy.utils.wx.MessageUtil;
import com.zzyy.utils.wx.WXBizMsgCrypt;
import com.zzyy.utils.wx.WxTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
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
import java.util.*;
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

        if (StringUtils.equals("component_verify_ticket", type)) {
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

        if (StringUtils.equals("authorized", type)) {

            Element authorizerAppid = re.element("AuthorizerAppid");
            String appidStringValue = authorizerAppid.getStringValue();

            Element authorizationCode = re.element("AuthorizationCode");
            String codeStringValue = authorizationCode.getStringValue();

            Element preAuthCode = re.element("PreAuthCode");
            String preAuthCodeStringValue = preAuthCode.getStringValue();

            //查询是否已经存在
            WxVerifyTicket exist = wxMapper.findByKey(appIdStringValue, type);

            if (exist != null) {

                exist.setAuthorizerAppid(appidStringValue);
                exist.setAuthorizationCode(codeStringValue);
                exist.setPreAuthCode(preAuthCodeStringValue);
                wxMapper.updateVerifyTicket(exist);
            } else {
                WxVerifyTicket vt = new WxVerifyTicket();

                vt.setAppId(appIdStringValue);
                vt.setInfoType(type);
                vt.setAuthorizerAppid(appidStringValue);
                vt.setAuthorizationCode(codeStringValue);
                vt.setPreAuthCode(preAuthCodeStringValue);

                wxMapper.saveVerifyTicket(vt);
            }


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

        String preCode = WxTokenUtils.sendPost(url, params.toJSONString(), null);
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

        //appid_username_dbid_tenantid

        String auth_code = request.getParameter("auth_code");
        String expires_in = request.getParameter("expires_in");
        String params = request.getParameter("params");
        String dbid = (String) request.getAttribute("dbid");
        dbid = StringUtils.trim(dbid);

        JSONObject info = new JSONObject();
        if (StringUtils.isNotBlank(params)) {
            info = JSONObject.parseObject(params);
        }
        log.info("参数info:" + info.toJSONString());

        String username = (String) request.getAttribute("username");
        String tenantid = (String) request.getAttribute("tenantid");

        //立即调用获取授权信息接口-获取authorizer_access_token和authorizer_refresh_token
        String token = getComponentToken(WxTokenUtils.APPID);


        String url = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=" + token;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("component_appid", WxTokenUtils.APPID);
        jsonObject.put("authorization_code", auth_code);

        String res = WxTokenUtils.sendPost(url, jsonObject.toJSONString(), new HashMap<>());
        log.info("使用授权码获取授权信息返回，res:" + res);
        if (StringUtils.isBlank(res)) {
            throw new CustomException("502", "获取授权信息失败");
        }

        JSONObject object = JSONObject.parseObject(res);
        if (!object.containsKey("authorization_info")) {
            throw new CustomException("502", "获取授权信息错误");
        }

        JSONObject authorization_info = object.getJSONObject("authorization_info");
        WxVerifyTicket wt = new WxVerifyTicket();
        wt.setDbid(dbid);
        wt.setUsername(username);
        wt.setTenantId(tenantid);
        wt.setAppId(WxTokenUtils.APPID);
        wt.setInfoType("authorization_info");
        wt.setAuthorizerAppid(authorization_info.getString("authorizer_appid"));
        wt.setAccessToken(authorization_info.getString("authorizer_access_token"));
        wt.setRefreshToken(authorization_info.getString("authorizer_refresh_token"));

        wxMapper.saveVerifyTicket(wt);
        //这里需要开启一个定时任务定时刷新accesstoken和erfreshtoken
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                log.info("定时任务刷新令牌......");
                WxVerifyTicket auth_info = wxMapper.findAccessToken(WxTokenUtils.APPID,
                        authorization_info.getString("authorizer_appid"));

                if (auth_info != null) {

                    String componentToken = getComponentToken(WxTokenUtils.APPID);
                    String reUrl = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=" + componentToken;
                    JSONObject param = new JSONObject();
                    param.put("component_appid", WxTokenUtils.APPID);
                    param.put("authorizer_appid", auth_info.getAuthorizerAppid());
                    param.put("authorizer_refresh_token", auth_info.getRefreshToken());

                    String s1 = WxTokenUtils.sendPost(reUrl, param.toJSONString(), new HashMap<>());
                    log.info("获取/刷新接口调用令牌返回，res:" + s1);
                    if (StringUtils.isNotBlank(s1)) {
                        JSONObject res = JSONObject.parseObject(s1);
                        if (res.containsKey("authorizer_access_token")) {

                            auth_info.setAccessToken(res.getString("authorizer_access_token"));
                            auth_info.setRefreshToken(res.getString("authorizer_refresh_token"));

                            wxMapper.updateVerifyTicket(auth_info);
                        }
                    }
                }

            }
        }, new Date(), 7000000);//单位为毫秒


        //获取到正经回调之后查询授权码和授权appid调用V7接口
        //1、查询
//        Map<String, Object> params = new HashMap<>();
//        params.put("appId", appId);
//        params.put("infoType", "authorized");
//        params.put("authCode", auth_code);
//        WxVerifyTicket wxVerifyTicket = wxMapper.findByParams(params);
//        //2、todo 调用api将授权回调信息保存至V7
//        Map<String, String> header = new HashMap<>();
//
//        header.put("accountId", dbid);
//        header.put("tenantId", tenanId);
//        header.put("userName", username);
//
//        JSONObject obj = new JSONObject();
//
//        obj.put("appid", appId);
//        obj.put("infotype", "authorized");
//        obj.put("authorizerappid", wxVerifyTicket.getAuthorizerAppid());
//        obj.put("authorizationcode", auth_code);
//        obj.put("preauthcode", wxVerifyTicket.getPreAuthCode());
//
//        url = "https://tf-feature1.jdy.com/ierp/innernal-api/app/mb/wx_auth";
//        String s1 = WxTokenUtils.sendPost(url, obj.toJSONString(), header);
//        log.info("调用V7api返回：" + s1);


    }

    private String getComponentToken(String appId) {

        String token = "";
        WxVerifyTicket component_verify_ticket = wxMapper.findByKey(appId, "component_verify_ticket");
        if (component_verify_ticket != null) {
            String ticket = component_verify_ticket.getTicket();

            String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("component_appid", appId);
            jsonObject.put("component_appsecret", WxTokenUtils.APPSECRET);
            jsonObject.put("component_verify_ticket", ticket);

            String res = WxTokenUtils.sendPost(url, jsonObject.toJSONString(), new HashMap<>());

            log.info("获取开放平台令牌返回，res:" + res);
            if (StringUtils.isBlank(res)) {
                throw new CustomException("500", "获取开放平台令牌失败");
            }

            JSONObject object = JSONObject.parseObject(res);

            if (object.containsKey("component_access_token")) {
                token = object.getString("component_access_token");
            } else {
                throw new CustomException("501", "获取开放平台令牌错误");
            }


        }

        if (StringUtils.isBlank(token)) {
            throw new CustomException("501", "获取开放平台令牌为空");
        }

        return token;
    }

    @Override
    public String getTicket() {

        WxVerifyTicket component_verify_ticket = wxMapper.findByKey(APPID, "component_verify_ticket");

        if (component_verify_ticket == null) {
            return "not fount";
        } else {
            return component_verify_ticket.getTicket();
        }
    }

    @Override
    public String getAccess(String dbid) {

        Map<String, Object> params = new HashMap<>();
        params.put("appId", WxTokenUtils.APPID);
        params.put("infoType", "authorization_info");
        params.put("dbid", dbid);

        WxVerifyTicket authorization_info = wxMapper.findByParams(params);
        if (authorization_info != null) {
            return authorization_info.getAccessToken();
        } else {
            return "";
        }
    }

    @Override
    public JSONObject getcomptoken() {

        JSONObject res = new JSONObject();

        //获取令牌
        Object o = redisTemplate.opsForValue().get("accessToken");

        if (o != null) {
            res.put("component_access_token", o);
            return res;
        }

        //查询ticket
        WxVerifyTicket component_verify_ticket = wxMapper.findByKey(WxTokenUtils.APPID, "component_verify_ticket");
        if (component_verify_ticket != null) {
            String ticket = component_verify_ticket.getTicket();
            String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

            JSONObject param = new JSONObject();
            param.put("component_appid", WxTokenUtils.APPID);
            param.put("component_appsecret", WxTokenUtils.APPSECRET);
            param.put("component_verify_ticket", ticket);

            String s = WxTokenUtils.sendPost(url, param.toJSONString(), new HashMap<>());

            log.info("查询开放平台token返回：" + s);

            if (StringUtils.isBlank(s)) {
                throw new CustomException("500", "查询开放平台token失败，返回为空");
            }

            res = JSONObject.parseObject(s);
        }
        if (res.containsKey("errcode")) {
            throw new CustomException("500", "查询开放平台返回失败" + res.toJSONString());
        }
        String component_access_token = res.containsKey("component_access_token") ?
                res.getString("component_access_token") : "";

        redisTemplate.opsForValue().set("accessToken", component_access_token, 100, TimeUnit.MINUTES);

        return res;
    }

    @Override
    public JSONObject checkAuthorize(String dbid) {

        JSONObject res = new JSONObject();
        res.put("isAuth", false);
        //查询授权信息
        Map<String, Object> params = new HashMap<>();
        params.put("dbid", dbid);
        params.put("infoType", "authorization_info");
        params.put("appId", WxTokenUtils.APPID);
        WxVerifyTicket byParams = wxMapper.findByParams(params);

        if (byParams != null) {

            //获取开放平台token
            JSONObject getcomptoken = getcomptoken();
            String component_access_token = getcomptoken.getString("component_access_token");
            String appId = byParams.getAppId();
            String authorizerAppid = byParams.getAuthorizerAppid();

            String url = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=" + component_access_token;

            JSONObject pam = new JSONObject();
            pam.put("component_appid", appId);
            pam.put("authorizer_appid", authorizerAppid);

            String s = WxTokenUtils.sendPost(url, pam.toJSONString(), new HashMap<>());
            log.info("查询授权信息返回:" + s);

            if (StringUtils.isBlank(s)) {
                throw new CustomException("500", "查询授权信息异常，返回为空");
            }
            res = JSONObject.parseObject(s);

            if (res.containsKey("errcode")) {
                throw new CustomException("500", "查询授权信息异常，返回错误" + s);
            }

            res.put("isAuth", true);
        }

        return res;
    }

    @Override
    public JSONObject recall(String dbid) {

        //查询授权信息
        Map<String, Object> params = new HashMap<>();
        params.put("dbid", dbid);
        params.put("infoType", "authorization_info");
        params.put("appId", WxTokenUtils.APPID);
        WxVerifyTicket byParams = wxMapper.findByParams(params);

        JSONObject getcomptoken = getcomptoken();
        String url = "https://api.weixin.qq.com/cgi-bin/open/unbind?access_token=" + getcomptoken;

        JSONObject param = new JSONObject();
        param.put("appid", byParams.getAuthorizerAppid());
        param.put("open_appid", byParams.getAppId());

        String s = WxTokenUtils.sendPost(url, param.toJSONString(), new HashMap<>());
        log.info("微信解绑返回：" + s);
        if (StringUtils.isBlank(s)) {
            throw new CustomException("500", "微信解绑异常，返回为空");
        }
        JSONObject object = JSONObject.parseObject(s);

        int errcode = object.containsKey("errcode") ? object.getIntValue("errcode") : 500;
        if (errcode != 0) {
            throw new CustomException("500", "微信解绑失败" + s);
        }

        return object;
    }

    @Override
    public void notify(String appId, HttpServletRequest request) {

//        if ("GET".equals(request.getMethod()) && validateToken(appId, request)) {
//            log.info("推送消息失败，请求验证不通过");
//        }

        Map<String, Object> params = new HashMap<>();
        params.put("appId", WxTokenUtils.APPID);
        params.put("infoType", "authorization_info");
        params.put("authorizerAppid", appId);
        WxVerifyTicket byParams = wxMapper.findByParams(params);

        String dbid = "";
        String username = "";
        String tenantId = "";
        if (byParams != null) {
            dbid = byParams.getDbid();
            username = byParams.getUsername();
            tenantId = byParams.getTenantId();
        }


        try {
            JSONObject jsonObject = MessageUtil.parseXML(request);
            log.info("微信推送消息：" + jsonObject.toJSONString());

            if (jsonObject.containsKey("Encrypt")) {
                String encrypt = jsonObject.getString("Encrypt");
                WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(TOKEN, KEY, APPID);
                String decrypt = wxBizMsgCrypt.decrypt(encrypt);
                log.info("解密后推送参数:" + decrypt);
                jsonObject = xml2Json(decrypt);
            }

            //根据APPID获取到绑定的V7用户
            Map<String, String> header = new HashMap<>();
            header.put("userName", username);
            header.put("tenantId", tenantId);
            header.put("accountId", dbid);

            String url = "https://tf-feature1.jdy.com/ierp/innernal-api/app/mb/wx_notify";


            String s = WxTokenUtils.sendPost(url, jsonObject.toJSONString(), header);
            log.info("推送V7返回：" + s);


        } catch (Exception e) {
            log.error("解析微信推送消息失败", e);
        }

    }

    @Override
    public JSONObject wechatRoute(String dbid, HttpServletRequest request) throws Exception {

        //校验参数
        JSONObject params = new JSONObject();
        String requestPostStr = getRequestPostStr(request);
        if (StringUtils.isNotBlank(requestPostStr)) {
            params = JSONObject.parseObject(requestPostStr);
        } else {
            throw new CustomException("500", "参数解析异常，内容为空");
        }

        String url = params.containsKey("url") ? params.getString("url") : "";
        String body = params.containsKey("body") ? params.getString("body") : "";

        //根据dbid获取到头信息
        Map<String, String> header = getHeaderByDBID(dbid);

        String res = WxTokenUtils.sendPost(url, body, header);
        log.info("转发V7请求返回" + res);
        if (StringUtils.isNotBlank(res)) {
            return JSONObject.parseObject(res);
        } else {
            return new JSONObject();
        }
    }

    @Override
    public JSONObject rechargeList(String dbid, HttpServletRequest request) {

        //根据dbid获取到头信息
        Map<String, String> header = getHeaderByDBID(dbid);
        String url = "https://tf-feature1.jdy.com/ierp/innernal-api/app/lsbd/query";
        String card_id = request.getParameter("card_id");
        String openidCard = request.getParameter("openid");
        String pageindex = request.getParameter("pageindex");
        String pagesize = request.getParameter("pagesize");

        String mobile = getMobile(header, card_id, openidCard);

        JSONObject params = new JSONObject();
        JSONArray filter = new JSONArray();

        params.put("selectFields", "id,storeid,rechargeamt,rechargetime");
        params.put("billType", "recharge_record");
        params.put("page", pageindex);
        params.put("pagesize", pagesize);

        JSONObject fi = new JSONObject();
        fi.put("name", "memberid.mobile");
        fi.put("cp", "=");
        fi.put("value", mobile);
        filter.add(fi);

        params.put("filter", filter);

        String res = WxTokenUtils.sendPost(url, params.toJSONString(), header);
        log.info("查询充值记录返回：" + res);
        if (StringUtils.isNotBlank(res)) {
            return JSONObject.parseObject(res);
        } else {
            return new JSONObject();
        }
    }

    @Override
    public JSONObject retailList(String dbid, HttpServletRequest request) {
        //根据dbid获取到头信息
        Map<String, String> header = getHeaderByDBID(dbid);
        String url = "https://tf-feature1.jdy.com/ierp/innernal-api/app/lsbd/query";

        String card_id = request.getParameter("card_id");
        String openidCard = request.getParameter("openid");
        String pageindex = request.getParameter("pageindex");
        String pagesize = request.getParameter("pagesize");

        String mobile = getMobile(header, card_id, openidCard);

        JSONObject params = new JSONObject();
        JSONArray filter = new JSONArray();

        params.put("selectFields", "id,date,storeid,bizType,billno,amount,memberid");
        params.put("billType", "store_bill");
        params.put("page", pageindex);
        params.put("pagesize", pagesize);
        params.put("orderby", "date desc");

        JSONObject fi = new JSONObject();
        fi.put("name", "memberid.mobile");
        fi.put("cp", "=");
        fi.put("value", mobile);
        filter.add(fi);
        JSONObject bizType = new JSONObject();
        bizType.put("name", "bizType");
        bizType.put("cp", "in");
        bizType.put("value", new int[]{62, 63});

        params.put("filter", filter);

        String res = WxTokenUtils.sendPost(url, params.toJSONString(), header);
        log.info("查询消费记录返回：" + res);
        if (StringUtils.isNotBlank(res)) {
            return JSONObject.parseObject(res);
        } else {
            return new JSONObject();
        }
    }

    private String getMobile(Map<String, String> header, String card_id, String openidCard) {

        String url = "https://tf-feature1.jdy.com/ierp/innernal-api/app/lsbd/query";

        String mobile = "";
        JSONObject params = new JSONObject();
        //获取手机号
        params.put("selectFields", "id,mobile");
        params.put("billType", "wx_mb_drawcard");
        JSONArray filter = new JSONArray();
        JSONObject openid = new JSONObject();
        openid.put("name", "openid");
        openid.put("cp", "=");
        openid.put("value", openidCard);
        filter.add(openid);

        JSONObject cardId = new JSONObject();
        cardId.put("name", "cardid");
        cardId.put("cp", "=");
        cardId.put("value", card_id);
        filter.add(cardId);

        params.put("filter", filter);

        String res = WxTokenUtils.sendPost(url, params.toJSONString(), header);
        log.info("查询手机号返回：" + res);

        JSONObject object = JSONObject.parseObject(res);
        boolean success = object.containsKey("success") ? object.getBooleanValue("success") : false;
        if (success) {
            JSONObject data = object.getJSONObject("data");
            JSONArray array = data.getJSONArray("rows");
            JSONObject row = array.size() > 0 ? array.getJSONObject(0) : new JSONObject();
            mobile = row.containsKey("mobile") ? row.getString("mobile") : "";

        } else {
            throw new CustomException("500", "身份校验异常,查询不到手机号");
        }

        return mobile;
    }

    private Map<String, String> getHeaderByDBID(String dbid) {
        Map<String, Object> pa = new HashMap<>();
        pa.put("dbid", dbid);
        pa.put("appId", WxTokenUtils.APPID);
        pa.put("infoType", "authorization_info");
        WxVerifyTicket byParams = wxMapper.findByParams(pa);

        Map<String, String> header = new HashMap<>();
        header.put("accountId", dbid);
        header.put("tenantId", byParams.getTenantId());
        header.put("userName", byParams.getUsername());

        return header;
    }

    protected static String getRequestPostStr(HttpServletRequest request) throws IOException {
        byte[] buffer = getRequestPostBytes(request);
        if (buffer.length < 2)
            return null;
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    protected static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return new byte[0];
        }
        byte[] buffer = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = request.getInputStream().read(buffer, i, contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }


    public Boolean validateToken(String appId, HttpServletRequest request) {

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");

        String[] str = {appId, timestamp, nonce};
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
        log.info("token  digest=" + digest);
        log.info("token  signature=" + signature);

        // 确认请求来至微信
        if (digest.equals(signature)) {
            return true;
        }
        return false;
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


    public JSONObject xml2Json(String xmlStr) {

        JSONObject res = new JSONObject();
        if (StringUtils.isBlank(xmlStr)) {
            return res;
        }

        Document documentDecrypt = null;
        try {
            documentDecrypt = DocumentHelper.parseText(xmlStr);
        } catch (DocumentException e) {
            log.error("xml2Json解析xml异常：", e);
        }
        Element re = documentDecrypt.getRootElement();

        List<Element> elements = re.elements();

        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            String elementName = element.getName();
            String stringValue = element.getStringValue();
            System.out.println(stringValue);

            res.put(elementName, stringValue);

        }

        System.out.println(res.toJSONString());
        return res;

    }

}
