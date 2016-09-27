package com.javarush.test.level14.lesson08.home09;

/**
 * Created by pdudenkov on 30.10.15.
 */
public class USD extends Money
{
    @Override
    public String getCurrencyName()
    {
        return "USD";
    }

    public USD(double amount) {
        super(amount);
    }
}
