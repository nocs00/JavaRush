package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pdudenkov on 09.03.2016.
 */
public class CurrencyManipulator
{
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations == null)
            denominations = new HashMap<>();

        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, count + denominations.get(denomination));
        } else
            denominations.put(denomination, count);

    }

    public boolean hasMoney() {
        for (Integer number : denominations.values())
            if (number > 0) return true;

        return false;
    }

    public int getTotalAmount() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            total += entry.getKey()*entry.getValue();
        }
        return total;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException, ConcurrentModificationException {
        Map<Integer, Integer> withdrawMap = new HashMap<>();
        Map<Integer, Integer> availableBills = new HashMap<>(denominations);
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet())
        {
            if (entry.getValue() == 0) {
                availableBills.remove(entry.getKey());
            }
        }


        while (expectedAmount != 0)
        {
            if (availableBills.size() == 0)
                throw new NotEnoughMoneyException();

            boolean denominationAvailable = false;
            for (Integer denomination : availableBills.keySet())
            {
                if (denomination <= expectedAmount)
                {
                    denominationAvailable = true;
                    break;
                }
            }
            if (!denominationAvailable)
                throw new NotEnoughMoneyException();

            int max = 0;
            int amount = 0;
            for (Integer integer : availableBills.keySet())
            {
                if (max < integer)
                {
                    max = integer;
                }
            }
            if (availableBills.containsKey(max))
                amount = availableBills.get(max);


            if (max > expectedAmount)
                availableBills.remove(max);
            else if (amount > 0)
            {
                int tAmount = 0;
                while (amount > 0 && max <= expectedAmount)
                {
                    expectedAmount -= max;
                    amount--;
                    tAmount++;
                }
                if (tAmount > 0)
                    withdrawMap.put(max, tAmount);
                if (amount == 0)
                    availableBills.remove(max);
            }
        }

        for (Map.Entry<Integer, Integer> entry : withdrawMap.entrySet())
        {
            denominations.put(entry.getKey(), denominations.get(entry.getKey())-entry.getValue());
        }

        return withdrawMap;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }
}