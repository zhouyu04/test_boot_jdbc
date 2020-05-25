package com.zzyy.DesignModule.BuilderPattern;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 10:53
 * @Description:
 */
public abstract class ColdDrink implements Item {


    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract double price();

}
