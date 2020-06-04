package com.zzyy.utils.wx;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: zhouyu
 * @Date: 2020/5/27 15:37
 * @Description:
 */
@Slf4j
public class WxTokenUtils {

    public static final String APPID = "wxb6051228351f2072";
    public static final String APPSECRET = "c61f5480f2a9e5d959391b7a0203b697";


    //获取令牌
    public static String getAccessToken(String ticket) {

        String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
        JSONObject params = new JSONObject();
        params.put("component_appid", APPID);
        params.put("component_appsecret", APPSECRET);
        params.put("component_verify_ticket", ticket);

        String token = sendPost(url, params.toJSONString(), null);
        log.info("获取令牌返回：" + token);
        return token;

    }


    public static String sendPost(String url, String params, Map<String, String> header) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            if (header != null) {
                Iterator<String> iterator = header.keySet().iterator();
                while (iterator.hasNext()) {
                    String next = iterator.next();
                    conn.setRequestProperty(next, header.get(next));
                }
            }

            conn.setReadTimeout(15000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            log.info("请求地址:" + realUrl.getPath() + "请求参数" + params);
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("发送 POST 请求出现异常！" + e);
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                log.error("关流异常", ex);
            }
        }
        return result;
    }

}
