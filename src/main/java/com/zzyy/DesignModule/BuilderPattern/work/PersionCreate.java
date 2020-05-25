package com.zzyy.DesignModule.BuilderPattern.work;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 14:16
 * @Description:
 */
public abstract class PersionCreate extends GamePersion{


    protected GamePersion gamePersion = new GamePersion();

    public abstract void createName();

    public abstract void createFace();

    public abstract void createGender();

    public abstract void createCloth();

    public GamePersion getPersion(){

        return gamePersion;
    }
}
