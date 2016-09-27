package com.javarush.test.level26.lesson15.big01;

import java.util.*;

/**
 * Created by pdudenkov on 09.03.2016.
 */
public class CurrencyManipulatorFactory
{
    private static Map<String, CurrencyManipulator> manipulators = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        CurrencyManipulator manipulator = null;

        if (manipulators.containsKey(currencyCode))
            return manipulators.get(currencyCode);

        manipulator = new CurrencyManipulator(currencyCode);
        manipulators.put(currencyCode, manipulator);

        return manipulator;
    }

    public static boolean hasMoney() {
        if (manipulators.size() == 0)
            return false;
        return true;
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return manipulators.values();
    }

    private CurrencyManipulatorFactory() {

    }
}
