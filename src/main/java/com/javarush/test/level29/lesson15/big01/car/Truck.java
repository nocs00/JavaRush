package com.javarush.test.level29.lesson15.big01.car;

/**
 * Created by pdudenkov on 27.06.2016.
 */
public class Truck extends Car
{
    public Truck(int numberOfPassengers)
    {
        super(TRUCK, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed()
    {
        final int MAX_TRUCK_SPEED = 80;
        return MAX_TRUCK_SPEED;
    }
}
