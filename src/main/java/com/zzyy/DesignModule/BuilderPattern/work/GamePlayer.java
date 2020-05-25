package com.zzyy.DesignModule.BuilderPattern.work;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 14:36
 * @Description:
 */
public class GamePlayer {

    private PersionCreate persionCreate;

    public GamePlayer(PersionCreate persionCreate) {
        this.persionCreate = persionCreate;
    }


    public GamePersion create() {

        persionCreate.createFace();
        persionCreate.createCloth();
        persionCreate.createGender();
        persionCreate.setName(persionCreate.getName());
        return persionCreate;
    }

    public void show() {

        System.out.println("玩家:" + persionCreate.getName() + ",性别:" + persionCreate.getGender()
                + ",脸型:" + persionCreate.getFace() + ",穿着:" + persionCreate.getCloth());

    }

}
