package com.zzyy.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 11:01
 * @Description:
 */
public class PropertiesUtil {

    private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
    private static Properties props;
//项目根目录文件夹内读取
    // static {
    //     if (props == null) {
    //         props = new Properties();
    //         try {
    //             props.load(new FileInputStream("/testDemo/config/test_config.properties"));
    //         } catch (IOException e) {
    //             log.error("配置文件读取异常", e);
    //         }
    //     }
    // }

    //resource文件夹内读取
    static {
        String fileName = "price.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            log.error("配置文件读取异常", e);
        }
    }

    /**
     * 根据配置文件中的key获取value
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        String value = props.getProperty(key.trim());
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return value.trim();
    }

    /**
     * 根据配置文件中的key获取value (当获取不到值赋予默认值)
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        String value = props.getProperty(key.trim());
        if (StringUtils.isEmpty(value)) {
            value = defaultValue;
        }
        return value.trim();
    }


    public static void main(String[] args) {
        System.out.println("配置文件中有key&value：" + PropertiesUtil.getProperty("ver_burger"));
        System.out.println("配置文件无有key&value，赋予默认值" + PropertiesUtil.getProperty("test.numberNone", "默认值 JCccc"));
    }
}
