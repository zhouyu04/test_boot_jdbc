package com.zzyy.test_boot_jdbc;

import com.alibaba.fastjson.JSONObject;
import com.zzyy.utils.wx.WxTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhouyu
 * @Date: 2019/4/25 09:18
 * @Description:
 */
public class CommonTest {


    @Test
    public void testv7() {


        //2、todo 调用api将授权回调信息保存至V7
        Map<String, String> header = new HashMap<>();

        header.put("accountId", "19002");
        header.put("tenantId", "jdy");
        header.put("userName", "administrator");

        JSONObject obj = new JSONObject();

        obj.put("appid", "1232321");
        obj.put("infotype", "authorized");
        obj.put("authorizerappid", "appid");
        obj.put("authorizationcode", "code");
        obj.put("preauthcode", "pre");

        String url = "https://tf-feature1.jdy.com/ierp/innernal-api/app/mb/wx_auth";
        String s1 = WxTokenUtils.sendPost(url, obj.toJSONString(), header);
        System.out.println(s1);
    }


    @Test
    public void testStringBuffer() {

        StringBuffer temp = new StringBuffer();
        temp.append("  消费积分规则：");

        String s = temp.toString();
        boolean b = s == null;
        System.out.println(b);
        System.out.println(temp);
        System.out.println(s);
        boolean notBlank = StringUtils.isNotBlank(s);
        System.out.println(notBlank);

    }

    @Test
    public void test02() {
        double d = 1.1;

        BigDecimal bd1 = new BigDecimal(d); // Noncompliant; see comment above
        BigDecimal bd2 = new BigDecimal(1.1); // Noncompliant; same result
        System.out.println(bd1);
        System.out.println(bd2);
    }

    @Test
    public void test03() {
        String sign = "sadsada";

        boolean notBlank = StringUtils.isNotBlank(sign);
        System.out.println(notBlank);
        if (StringUtils.isNotBlank(sign)) {
            sign = sign.toUpperCase();
            System.out.println(sign);
        }
    }

    @Test
    public void test04() {


    }
}
