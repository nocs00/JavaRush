package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdudenkov on 21.07.2016.
 */
public class Order
{
    private Tablet tablet;
    private List<Dish> dishes;

    public Order(Tablet tablet) throws Exception
    {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime() {
        int total = 0;
        for (Dish dish : dishes) {
            total += dish.getDuration();
        }
        return total;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    @Override
    public String toString()
    {
        if (dishes.isEmpty()) return "";
        StringBuilder result = new StringBuilder();
        result.append("Your order: [");
        boolean first = true;
        for (Dish dish : dishes) {
            if (first) first = !first;
            else result.append(", ");
            result.append(dish.name());
        }
        result.append("] of ");
        result.append(tablet);
        return result.toString();
    }
}
