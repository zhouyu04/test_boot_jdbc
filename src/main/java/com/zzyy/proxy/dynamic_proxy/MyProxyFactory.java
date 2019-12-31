package com.zzyy.proxy.dynamic_proxy;

import java.lang.reflect.Proxy;

/**
 * @Auther: zhouyu
 * @Date: 2019/12/31 17:11
 * @Description:
 */
public class MyProxyFactory {


    public static Object getProxy(Object target){

        DynamicProxyHandler handler = new DynamicProxyHandler();

        handler.setObject(target);

        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);

        return proxy;
    }


}
