package com.zzyy.proxy.static_proxy;

/**
 * @Auther: zhouyu
 * @Date: 2019/12/31 16:49
 * @Description:
 */
public class StaticServiceProxy implements StaticService{

    private StaticService staticService;

    public StaticServiceProxy(StaticService staticService) {
        this.staticService = staticService;
    }

    @Override
    public void eat() {

        System.out.println("吃饭之前......");
        staticService.eat();
        System.out.println("吃饭之后......");

    }
}
