package com.zzyy.proxy.static_proxy;

/**
 * @Auther: zhouyu
 * @Date: 2019/12/31 16:46
 * @Description:
 */
public class StaticServiceImpl implements StaticService {


    @Override
    public void eat() {

        System.out.println("本体吃饭....");
    }
}
