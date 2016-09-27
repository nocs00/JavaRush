package com.javarush.test.level14.lesson08.home09;

/**
 * Created by pdudenkov on 30.10.15.
 */
public class Hrivna extends Money
{
    @Override
    public String getCurrencyName()
    {
        return "HRN";
    }

    public Hrivna(double amount) {
        super(amount);
    }
}
