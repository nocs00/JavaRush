package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdudenkov on 22.07.2016.
 */
public final class ConsoleHelper
{
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        String enteredLine = null;
        writeMessage("Please choose Dish [" + Dish.allDishesToString() + "] or enter 'exit' to return:");
        while (true) {
            enteredLine = readString();
            if (enteredLine.equals("exit")) break;
            else {
                try {
                    dishes.add(Dish.valueOf(enteredLine));
                } catch (IllegalArgumentException e) {
                    ConsoleHelper.writeMessage(enteredLine + " is not detected");
                }
            }
        }
        return dishes;
    }
}
