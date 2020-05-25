package com.zzyy.DesignModule.BuilderPattern;

import com.zzyy.utils.PropertiesUtil;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 13:41
 * @Description:
 */
public class Pepsi extends ColdDrink{
    @Override
    public String name() {
        return "百事可乐";
    }

    @Override
    public double price() {
        return Double.valueOf(PropertiesUtil.getProperty("pepsi_drink"));
    }
}
