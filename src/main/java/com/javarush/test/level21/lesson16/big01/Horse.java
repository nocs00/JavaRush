package com.javarush.test.level21.lesson16.big01;

/**
 * Created by pdudenkov on 05.02.2016.
 */
public class Horse
{
    private String name;
    private double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public void move() {
        speed = 20*Math.random()*Math.random();
        distance += speed;
    }

    public void print() {
        int dist = (int)distance;
        for (int i = 0; i < dist; i++)
            System.out.print(".");
        System.out.println(name);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }
}
