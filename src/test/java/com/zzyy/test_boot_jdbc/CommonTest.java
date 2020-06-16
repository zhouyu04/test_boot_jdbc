package com.zzyy.test_boot_jdbc;

import com.alibaba.fastjson.JSONObject;
import com.zzyy.utils.wx.Uploadfile;
import com.zzyy.utils.wx.WxTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhouyu
 * @Date: 2019/4/25 09:18
 * @Description:
 */
public class CommonTest {


    @Test
    public void testPost(){

        String url = "https://api.weixin.qq.com/card/create?access_token=34_rwX9bK1GkylAV4e-DVg2h7c0f4qypRd52NvX8SSIZfD8-Zkc64y9LKhd0V_QUX9U7bgkIGEFHYC2U816aLYgVcLWP6IgGd6vfwSyfU-fqGls_vMk8Sp5qdIWFUrH0YmyOfGZpu6VFnR5qxqWIUEdAKDZLV";

        String pa = "{\"card\":{\"aaa\":{\"custom_cell1\":{\"name\":\"充值记录\",\"tips\":\"充值记录\",\"url\":\"www.baidu.com\"},\"prerogative\":\"消费积分规则\",\"custom_cell2\":{\"name\":\"消费记录\",\"tips\":\"消费记录\",\"url\":\"www.baidu.com\"},\"supply_bonus\":true,\"wx_activate_after_submit_url\":\"https://retail.jdy.comopenapi/weChat/getUserInfo/administrator/1?dbid=19204\",\"base_info\":{\"color\":\"Color010\",\"logo_url\":\"http://mmbiz.qpic.cn/sz_mmbiz_jpg/eic4l8gMFD89usBX3XdHQXjpsQTX5lnicoYX2qcmRWMbkXFVtKiahEGG1uTIlCduib3QibRT6xGXwvicqJgHNEhFhn0A/0\",\"promotion_url_sub_title\":\"优惠券\",\"promotion_url_name\":\"优惠券\",\"promotion_url\":\"www.retail.jdy.com\",\"brand_name\":\"测试会员卡\",\"date_info\":{\"type\":\"DATE_TYPE_PERMANENT\"},\"title\":\"零售V7\",\"sku\":{\"quantity\":50000000},\"code_type\":\"CODE_TYPE_QRCODE\",\"notice\":\"消费积分规则\"},\"supply_balance\":false,\"wx_activate\":true,\"wx_activate_after_submit\":true},\"card_type\":\"MEMBER_CARD\"}}";

        String s = Uploadfile.doPost(url, pa);

        System.out.println(s);

    }


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
