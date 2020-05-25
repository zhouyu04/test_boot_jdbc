package com.zzyy.test_boot_jdbc;

import com.zzyy.DesignModule.BuilderPattern.Meal;
import com.zzyy.DesignModule.BuilderPattern.MealBuilder;
import com.zzyy.DesignModule.BuilderPattern.work.GamePersion;
import com.zzyy.DesignModule.BuilderPattern.work.GamePlayer;
import com.zzyy.DesignModule.BuilderPattern.work.PersionCreate;
import com.zzyy.DesignModule.BuilderPattern.work.PersionType;
import com.zzyy.proxy.dynamic_proxy.MyProxyFactory;
import com.zzyy.proxy.static_proxy.StaticService;
import com.zzyy.proxy.static_proxy.StaticServiceImpl;
import com.zzyy.proxy.static_proxy.StaticServiceProxy;
import com.zzyy.utils.Singleton;
import io.micrometer.core.instrument.util.MathUtils;
import org.junit.Test;

import java.util.Random;

/**
 * @Auther: zhouyu
 * @Date: 2019/12/31 16:53
 * @Description:
 */
public class TestProxy {

    @Test
    public void testRandom(){

        Random random = new Random();
        int i = random.nextInt(5);


    }

    @Test
    public void TestBuilder2(){

        PersionCreate persionCreate = new PersionType("云之迢迢");
        GamePlayer gamePlayer = new GamePlayer(persionCreate);
        GamePersion gamePersion = gamePlayer.create();
        gamePlayer.show();


    }


    @Test
    public void testBuilder() {

        MealBuilder mb = new MealBuilder();

        Meal meal = mb.prepareChickenMeal();
        double cost = meal.getCost();
        System.out.println("cost:" + cost);
        meal.showItems();


        Meal meal2 = mb.prepareVegMeal();
        double cost2 = meal2.getCost();
        System.out.println("cost2:" + cost2);
        meal2.showItems();

    }


    @Test
    public void testSingleton() {

        Singleton instance = Singleton.INSTANCE;
        instance.whateverMethod();
    }

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
