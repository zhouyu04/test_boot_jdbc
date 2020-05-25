package com.zzyy.DesignModule.BuilderPattern.work;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 14:26
 * @Description:
 */
public class PersionType extends PersionCreate {

    private String name;


    @Override
    public void createName() {
        this.setName(this.name);
    }

    public PersionType(String name) {
        this.name = name;
    }

    @Override
    public void createFace() {

        int i = random.nextInt(faces.length - 1);

        this.setFace(faces[i]);
    }

    @Override
    public void createGender() {

        int i = random.nextInt(genders.length - 1);
        this.setGender(genders[i]);
    }

    @Override
    public void createCloth() {
        int i = random.nextInt(clothes.length - 1);
        this.setCloth(clothes[i]);
    }
}
