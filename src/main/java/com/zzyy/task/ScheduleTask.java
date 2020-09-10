package com.zzyy.task;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhouyu
 * @Date: 2020/7/30 20:05
 * @Description:
 */
@Configuration
@EnableScheduling   // 2.开启定时任务
public class ScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);


    //用户名
    private static String Uid = "zhouyu0320";

    //接口安全秘钥
    private static String Key = "5479cba138884be3bae7";

    //手机号码，多个号码如13800000000,13800000001,13800000002
    private static String smsMob = "18273013312,13327306967";

    //短信内容
    private static String smsText = "验证码：8888";

    //3.添加定时任务
//    @Scheduled(cron = "* * * * * ?")
    //或直接指定时间间隔，例如：1800秒
    @Scheduled(fixedRate = 1800 * 1000)
    private void configureTasks() {

        logger.info("入园检测开始........");

        String url = "https://sc.chimelong.com/services/getStockData";

        JSONObject params = new JSONObject();
        params.put("date", "2020-10-01");
        params.put("no_year_card", "1");
        params.put("scenic_code", "GZ53");
        String s = doPost(url, params);
        JSONObject jsonObject = JSONObject.parseObject(s);
        logger.info(String.format("%s%s", "入园检查返回", jsonObject.toJSONString()));

        String code = jsonObject.containsKey("code") ? jsonObject.getString("code") : "";

        String message = jsonObject.containsKey("message") ? jsonObject.getString("message") : "入园检测变更通知";

        if (!StringUtils.equals("10001", code)) {
            //发送短信通知
            HttpClientUtil client = HttpClientUtil.getInstance();
            //UTF发送
            int result = client.sendMsgUtf8(Uid, Key, message, smsMob);
            if (result > 0) {
                logger.info("%s%s", "UTF8成功发送条数==", result);
            } else {
                logger.info(client.getErrorMsg(result));
            }
        }
    }


    public static String doPost(String url, Map<String, Object> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        logger.info("HTTP doPost reqData : {}", param);
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, (String) param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");

            logger.info("HTTP doPost resData : {}", resultString);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

}


