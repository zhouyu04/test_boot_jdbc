package com.zzyy.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther: zhouyu
 * @Date: 2019/12/31 16:57
 * @Description:
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("吃饭之前......");
        Object result = method.invoke(object, args);
        System.out.println("吃饭之后......");

        return result;
    }
}
