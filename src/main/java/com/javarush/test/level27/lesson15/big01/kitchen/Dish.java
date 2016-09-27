package com.javarush.test.level27.lesson15.big01.kitchen;

/**
 * Created by pdudenkov on 21.07.2016.
 */
public enum Dish
{
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);

    private int duration;

    Dish(int duration)
    {
        this.duration = duration;
    }

    public int getDuration()
    {
        return duration;
    }

    public static String allDishesToString() {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Dish dish : values()) {
            if (!first) result.append(", ");
            else first = false;

            result.append(dish.name());
        }
        return result.toString();
    }
}
