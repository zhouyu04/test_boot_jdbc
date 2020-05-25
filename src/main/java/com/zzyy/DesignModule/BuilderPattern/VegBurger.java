package com.zzyy.DesignModule.BuilderPattern;

import com.zzyy.utils.PropertiesUtil;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 10:55
 * @Description:
 */
public class VegBurger extends Burger {

    @Override
    public String name() {
        return "素食汉堡";
    }

    @Override
    public double price() {
        return Double.valueOf(PropertiesUtil.getProperty("ver_burger"));
    }
}
