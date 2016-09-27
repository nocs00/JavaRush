package com.javarush.test.level33.lesson05.home03;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Cat {
    public String name;
    public int age;
    public int weight;

    public Cat() {
    }
}