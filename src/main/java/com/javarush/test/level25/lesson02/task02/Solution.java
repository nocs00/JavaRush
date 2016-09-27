package com.javarush.test.level25.lesson02.task02;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* Машину на СТО не повезем!
Инициализируйте поле wheels используя данные из loadWheelNamesFromDB.
Обработайте некорректные данные.
Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            //init wheels here
            Set<Wheel> wheelSet = new HashSet<Wheel>();
            for (String s : loadWheelNamesFromDB()) {
                try
                {
                    Wheel w = Wheel.valueOf(s);
                    wheelSet.add(w);
                } catch (IllegalArgumentException e) {
                    System.out.println("Это не машина!");
                }
            }
            if (wheelSet.size() != 4) {
                throw new IllegalArgumentException();
            } else {
                wheels = new LinkedList<>();
                for (Wheel w : wheelSet) {
                    wheels.add(w);
                }
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args)
    {
        Car car = new Car();
        return;
    }
}
