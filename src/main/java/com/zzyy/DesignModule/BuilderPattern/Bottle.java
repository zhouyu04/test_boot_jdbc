package com.zzyy.DesignModule.BuilderPattern;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 10:51
 * @Description:
 */
public class Bottle implements Packing {

    @Override
    public String pack() {
        return "瓶装";
    }
}
