package com.zzyy.DesignModule.BuilderPattern;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 13:46
 * @Description:
 */
public class MealBuilder {


    public Meal prepareVegMeal() {

        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coco());
        return meal;
    }


    public Meal prepareChickenMeal() {

        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }

}
