package com.javarush.test.level29.lesson15.big01.human;

/**
 * Created by pdudenkov on 26.06.2016.
 */
public class UniversityPerson extends Human
{
    private University university;

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public UniversityPerson(String name, int age) {
        super(name,age);
    }
}
