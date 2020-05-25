package com.zzyy.DesignModule.BuilderPattern;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 10:52
 * @Description:
 */
public abstract class Burger implements Item {


    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract double price();
}
