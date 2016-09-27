package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by pdudenkov on 22.07.2016.
 */
public class Waitor implements Observer
{
    @Override
    public void update(Observable whoSent, Object whatSent)
    {
        ConsoleHelper.writeMessage(whatSent + " was cooked by " + whoSent);
    }
}
