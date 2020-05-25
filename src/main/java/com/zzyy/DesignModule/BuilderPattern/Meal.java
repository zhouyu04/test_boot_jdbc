package com.zzyy.DesignModule.BuilderPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhouyu
 * @Date: 2020/1/2 13:42
 * @Description:
 */
public class Meal {

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {

        items.add(item);
    }

    public double getCost() {

        double cost = 0;
        for (Item item : items) {
            double price = item.price();
            cost += price;
        }
        return cost;
    }

    public void showItems(){
        for (Item item : items) {
            System.out.print("Item : "+item.name());
            System.out.print(", Packing : "+item.packing().pack());
            System.out.println(", Price : "+item.price());
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
