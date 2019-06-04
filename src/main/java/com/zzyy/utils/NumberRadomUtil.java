package com.zzyy.utils;

import java.util.Random;

public class NumberRadomUtil
{

    static int[] defaultSeed =
            { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    /**
     * 生成无符号的随机数字字符串
     *
     * @param sd
     *            种子的最好不要超过1000个
     * @return
     */
    public static String getUnsignNumber(int[] sd)
    {

        int[] seed = sd;
        // 种子你可以随意生成，但不能重复
        if (null == sd || sd.length <= 0)
        {
            // 启用默认的seed
            seed = defaultSeed;
        }

        for (int i = 0; i < seed.length; i++)
        {
            seed[i] = Math.abs(seed[i]);
        }

        int[] ranArr = new int[seed.length];

        Random ran = new Random();

        // 数量你可以自己定义。

        for (int i = 0; i < seed.length; i++)
        {

            // 得到一个位置

            int j = ran.nextInt(seed.length - i);

            // 得到那个位置的数值

            ranArr[i] = seed[j];

            // 将最后一个未用的数字放到这里

            seed[j] = seed[seed.length - 1 - i];

        }
        StringBuffer strB = new StringBuffer();

        for (int i : ranArr)
        {
            strB.append(i);
        }
        return strB.toString();
    }


}
