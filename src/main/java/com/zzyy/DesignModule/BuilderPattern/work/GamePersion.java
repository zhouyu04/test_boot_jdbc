package com.zzyy.DesignModule.BuilderPattern.work;

import lombok.Data;

import java.util.Random;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 13:57
 * @Description:
 */
@Data
public class GamePersion {

    Random random = new Random();

    public String[] clothes = {"西装","裙子","泳衣","迷彩服","潜水服"};

    public String[] faces = {"大圆脸","国字脸","瓜子脸","巨大的脸"};

    public String[] genders = {"靓仔","靓女"};

    public String name;

    public String face;

    public String gender;

    public String cloth;

}
