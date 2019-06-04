package com.zzyy.test_boot_jdbc;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Auther: zhouyu
 * @Date: 2019/4/25 09:18
 * @Description:
 */
public class CommonTest {



    @Test
    public void testStringBuffer(){

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
    public void test02(){
        double d = 1.1;

        BigDecimal bd1 = new BigDecimal(d); // Noncompliant; see comment above
        BigDecimal bd2 = new BigDecimal(1.1); // Noncompliant; same result
        System.out.println(bd1);
        System.out.println(bd2);
    }

    @Test
    public void test03(){
        String sign = "sadsada";

        boolean notBlank = StringUtils.isNotBlank(sign);
        System.out.println(notBlank);
        if (StringUtils.isNotBlank(sign)){
            sign = sign.toUpperCase();
            System.out.println(sign);
        }
    }

    @Test
    public void test04(){


    }
}
