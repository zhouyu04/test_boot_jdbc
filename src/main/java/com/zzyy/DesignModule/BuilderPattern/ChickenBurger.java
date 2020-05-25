package com.zzyy.DesignModule.BuilderPattern;

import com.zzyy.utils.PropertiesUtil;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 13:33
 * @Description:
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "鸡肉汉堡";
    }

    @Override
    public double price() {

        return Double.valueOf(PropertiesUtil.getProperty("chicken_burger"));
    }
}
