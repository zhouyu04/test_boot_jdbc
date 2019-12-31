package com.zzyy.test_boot_jdbc;

import com.zzyy.proxy.dynamic_proxy.DynamicProxyHandler;
import com.zzyy.proxy.dynamic_proxy.MyProxyFactory;
import com.zzyy.proxy.static_proxy.StaticService;
import com.zzyy.proxy.static_proxy.StaticServiceImpl;
import com.zzyy.proxy.static_proxy.StaticServiceProxy;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @Auther: zhouyu
 * @Date: 2019/12/31 16:53
 * @Description:
 */
public class TestProxy {


    @Test
    public void testStaticProxy() {

        StaticService ss = new StaticServiceImpl();
        ss.eat();

        StaticServiceProxy sp = new StaticServiceProxy(ss);
        sp.eat();

    }

    @Test
    public void testDynamicProxy() {

        StaticService ss = new StaticServiceImpl();
//        StaticService serviceProxy = (StaticService) Proxy.newProxyInstance(StaticService.class.getClassLoader(),
//                new Class[]{StaticService.class}, new DynamicProxyHandler(ss));
//
//        serviceProxy.eat();


        StaticService proxy = (StaticService) MyProxyFactory.getProxy(ss);
        proxy.eat();

    }

}
