package com.zzyy.DesignModule.BuilderPattern;

import com.zzyy.utils.PropertiesUtil;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 13:38
 * @Description:
 */
public class Coco extends ColdDrink{


    @Override
    public String name() {
        return "可口可乐";
    }

    @Override
    public double price() {
        return Double.valueOf(PropertiesUtil.getProperty("coco_drink"));
    }
}
