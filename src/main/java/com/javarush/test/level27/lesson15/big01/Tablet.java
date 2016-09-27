package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by pdudenkov on 21.07.2016.
 */
public class Tablet extends Observable
{
    private final static Logger LOG = Logger.getLogger(Tablet.class.getName());
    private final int number;
    private AdvertisementManager advertisementManager;

    public Tablet(int number)
    {
        this.number = number;
    }

    public void createOrder() {
        try
        {
            Order order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            if (!order.isEmpty())
            {
                advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                setChanged();
                try
                {
                    advertisementManager.processVideos();
                } catch (NoVideoAvailableException e) {
                    LOG.log(Level.INFO, "No video is available for the order " + order);
                } catch (UnsupportedOperationException e) {

                }
                notifyObservers(order);
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    @Override
    public String toString()
    {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
