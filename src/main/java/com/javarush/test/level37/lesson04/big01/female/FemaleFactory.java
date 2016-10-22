package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

/**
 * Created by pdudenkov on 10/22/16.
 */
public class FemaleFactory implements AbstractFactory {
    public Human getPerson(int age) {
        if (age <= KidGirl.MAX_AGE) {
            return new KidGirl();
        }
        if (age <= TeenGirl.MAX_AGE) {
            return new TeenGirl();
        }
        return new Woman();
    }
}
