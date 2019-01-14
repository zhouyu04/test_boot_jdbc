package com.zzyy.test_boot_jdbc;

import org.junit.Test;

import java.util.Random;

public class RandomTest {


    @Test
    public void test() {

        System.out.println("军师" + Math.random() * 100);

        System.out.println("院长" + Math.random() * 100);

        System.out.println("毛" + Math.random() * 100);

    }

    @Test
    public void test2() {

        Random rm = new Random(10);


        System.out.println(rm.nextInt());
        System.out.println(rm.nextInt());
        System.out.println(rm.nextInt());

    }
}
