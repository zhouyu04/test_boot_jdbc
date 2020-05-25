package com.zzyy.DesignModule.BuilderPattern;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 10:47
 * @Description:
 */
public interface Item {

    public String name();

    public Packing packing();

    public double price();
}
