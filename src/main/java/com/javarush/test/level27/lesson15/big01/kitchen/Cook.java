package com.javarush.test.level27.lesson15.big01.kitchen;


import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by pdudenkov on 22.07.2016.
 */
public class Cook extends Observable implements Observer
{
    private String name;

    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public void update(Observable whoSent, Object whatSent)
    {
        Order order = (Order)whatSent;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time "+ order.getTotalCookingTime() + "min");
        setChanged();
        notifyObservers(whatSent);
    }

    @Override
    public String toString()
    {
        return name;
    }
}
